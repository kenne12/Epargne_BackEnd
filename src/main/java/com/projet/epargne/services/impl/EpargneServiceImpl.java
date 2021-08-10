package com.projet.epargne.services.impl;

import com.projet.epargne.exceptions.ObjectNotFoundException;
import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dao.RetraitRepository;
import com.projet.epargne.dao.VersementRepository;
import com.projet.epargne.dto.RetraitRequestDTO;
import com.projet.epargne.dto.VersementRequestDTO;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.entities.Client;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.exceptions.SoldeNegatifException;
import com.projet.epargne.mapper.RetraitMapper;
import com.projet.epargne.mapper.VersementMapper;
import com.projet.epargne.services.interfaces.EpargneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class EpargneServiceImpl implements EpargneService {

    private final CaisseRepository caisseRepository;
    private final VersementRepository versementRepository;
    private final RetraitRepository retraitRepository;
    private final ClientRepository clientRepository;

    @Override
    public Versement saveVersement(VersementRequestDTO versementRequestDTO) {
        Client c = clientRepository.findById(versementRequestDTO.getIdclient()).orElseThrow(() -> new ObjectNotFoundException("Client not found with id : " + versementRequestDTO.getIdclient()));

        this.updateCaisse("+", (int) versementRequestDTO.getMontant());
        Client cUpdate = this.updateClient(c.getIdclient().longValue(), versementRequestDTO.getMontant(), "+");

        Long id = null;
        try {
            id = versementRepository.nextValue();
        } catch (Exception e) {
            id = null;
        }

        Versement versement = VersementMapper.INSTANCE.fromRequestToEnity(versementRequestDTO);
        versement.setClient(c);
        versement.setSolde(cUpdate.getSolde());
        versement.setIdVersement(id == null ? 1L : id + 1);
        return versementRepository.save(versement);
    }

    @Override
    public Versement editVersement(VersementRequestDTO versementRequestDTO) {
        Versement versement = versementRepository.findById(versementRequestDTO.getIdVersement())
                .orElseThrow(() -> new ObjectNotFoundException("Versement not found with id : " + versementRequestDTO.getIdVersement()));
        this.updateCaisse("-", (int) versement.getMontant());
        this.updateClient(versement.getClient().getIdclient().longValue(), versement.getMontant(), "-");

        this.updateCaisse("+", (int) versementRequestDTO.getMontant());
        Client cUpdate = this.updateClient((long) versementRequestDTO.getIdclient(), versementRequestDTO.getMontant(), "-");
        versement.setSolde((double) cUpdate.getSolde());
        versement.setMontant(versementRequestDTO.getMontant());
        return versementRepository.save(versement);
    }

    @Override
    public void deleteVersement(Long idVersement) {
        Versement versement = versementRepository.findById(idVersement).orElseThrow(() -> new ObjectNotFoundException("Versement not found with id : " + idVersement));
        this.updateCaisse("-", (int) versement.getMontant());
        this.updateClient(versement.getClient().getIdclient().longValue(), versement.getMontant(), "-");
        versementRepository.deleteById(idVersement);
    }

    @Override
    public Retrait saveRetrait(RetraitRequestDTO retraitRequestDTO) {


        Client c = clientRepository.findById(retraitRequestDTO.getIdclient()).orElseThrow(() -> new ObjectNotFoundException("Client not found with id : " + retraitRequestDTO.getIdclient()));
        Double montantTotal = retraitRequestDTO.getMontant() + retraitRequestDTO.getCommission();
        updateCaisse("-", montantTotal.intValue());
        Client cUpdate = this.updateClient(c.getIdclient().longValue(), montantTotal, "-");

        Long nexId = null;

        Retrait retrait = RetraitMapper.INSTANCE.fromRequestToEntity(retraitRequestDTO);
        retrait.setClient(c);
        retrait.setSolde(cUpdate.getSolde());
        retrait.setMontant(montantTotal);
        try {
            nexId = retraitRepository.nextValue();
        }catch (Exception e){

        }

        retrait.setIdRetrait(nexId == null ? 1L : nexId + 1);
        return retraitRepository.save(retrait);
    }

    @Override
    public Retrait editRetrait(RetraitRequestDTO retraitRequestDTO) {
        Retrait retrait = retraitRepository.findById(retraitRequestDTO.getIdRetrait())
                .orElseThrow(() -> new ObjectNotFoundException("Retrait not found with id : " + retraitRequestDTO.getIdRetrait()));
        Double montantAvant = retrait.getMontant() + retrait.getCommission();
        this.updateCaisse("+", montantAvant.intValue());

        Double montantApres = retraitRequestDTO.getMontant() + retraitRequestDTO.getCommission();
        this.updateCaisse("-", montantApres.intValue());
        retrait.setMontant(montantApres);
        return this.retraitRepository.save(retrait);
    }

    @Override
    public void deleteRetrait(Long idRetrait) {
        Retrait retrait = retraitRepository.findById(idRetrait).orElseThrow(() -> new ObjectNotFoundException("Retrait not found with id : " + idRetrait));
        Double montant = retrait.getMontant() + retrait.getCommission();
        updateClient(retrait.getClient().getIdclient().longValue(), montant, "+");
        updateCaisse("+", montant.intValue());
        retraitRepository.deleteById(idRetrait);
    }

    private void updateCaisse(String signe, Integer montant) {
        Caisse caisse = caisseRepository.findById(1).get();
        if (signe.equals("+")) {
            caisse.setMontant(caisse.getMontant() + montant);
        } else {
            caisse.setMontant(caisse.getMontant() - montant);
        }
        caisseRepository.save(caisse);
    }

    private Client updateClient(Long idClient, Double montant, String signe) {
        Client client = clientRepository.findById(idClient.intValue()).get();
        if (signe.equals("+")) {
            client.setSolde(client.getSolde() + montant.intValue());
        } else {
            if (montant > client.getSolde()) {
                throw new SoldeNegatifException("Le solde est inférieur au montant sollicité");
            } else {
                client.setSolde(client.getSolde() - montant.intValue());
            }
        }
        return clientRepository.save(client);
    }
}
