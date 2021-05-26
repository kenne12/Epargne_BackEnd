package com.projet.epargne.services.impl;

import com.projet.epargne.ObjectNotFoundException;
import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dao.RetraitRepository;
import com.projet.epargne.dao.VersementRepository;
import com.projet.epargne.dto.RetraitRequest;
import com.projet.epargne.dto.VersementRequest;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.entities.Client;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.services.interfaces.EpargneService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Versement saveVersement(VersementRequest versementRequest) {
        Client c = clientRepository.findById(versementRequest.getIdclient()).orElseThrow(() -> new ObjectNotFoundException("Client non retrouvé : " + versementRequest.getIdclient()));

        this.updateCaisse("+", (int) versementRequest.getMontant());
        Client cUpdate = this.updateClient(c.getIdclient().longValue(), versementRequest.getMontant(), "+");

        Long id = versementRepository.nextValue();

        Versement versement = Versement.builder()
                .client(c)
                .date(versementRequest.getDate())
                .heure(versementRequest.getHeure())
                .montant(versementRequest.getMontant())
                .solde(cUpdate.getSolde())
                .idVersement(id == null ? 1L : id + 1)
                .build();
        return versementRepository.save(versement);
    }

    @Override
    public Versement editVersement(VersementRequest versementRequest) {
        Versement versement = versementRepository.findById(versementRequest.getIdVersement())
                .orElseThrow(() -> new ObjectNotFoundException("Versement not found with id : " + versementRequest.getIdVersement()));
        this.updateCaisse("-", (int) versement.getMontant());
        this.updateClient(versement.getClient().getIdclient().longValue(), versement.getMontant(), "-");

        this.updateCaisse("+", (int) versementRequest.getMontant());
        Client cUpdate = this.updateClient((long) versementRequest.getIdclient(), versementRequest.getMontant(), "-");
        versement.setSolde((double) cUpdate.getSolde());
        versement.setMontant(versementRequest.getMontant());
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
    public Retrait saveRetrait(RetraitRequest retraitRequest) {
        Client c = clientRepository.findById(retraitRequest.getIdclient()).orElseThrow(() -> new ObjectNotFoundException("Client non retrouvé : " + retraitRequest.getIdclient()));
        Double montant = retraitRequest.getMontant() + retraitRequest.getCommission();
        updateCaisse("-", montant.intValue());
        Client cUpdate = this.updateClient(c.getIdclient().longValue(), montant, "-");

        Retrait retrait = Retrait.builder()
                .client(c)
                .mois(null)
                .commission(retraitRequest.getCommission())
                .commissionAuto(retraitRequest.isCommissionAuto())
                .solde(cUpdate.getSolde())
                .montant(montant)
                .heure(retraitRequest.getHeure())
                .date(retraitRequest.getDate())
                .build();

        Long nexId = retraitRepository.nextValue();
        retrait.setIdRetrait(nexId == null ? 1L : nexId + 1);
        return retraitRepository.save(retrait);
    }

    @Override
    public Retrait editRetrait(RetraitRequest retraitRequest) {
        Retrait retrait = retraitRepository.findById(retraitRequest.getIdRetrait())
                .orElseThrow(()->new ObjectNotFoundException("Retrait not found with id : "+retraitRequest.getIdRetrait()));
        Double montantAvant = retrait.getMontant() + retrait.getCommission();
        this.updateCaisse("+", montantAvant.intValue());

        Double montantApres = retraitRequest.getMontant() + retraitRequest.getCommission();
        this.updateCaisse("-", montantApres.intValue());
        retrait.setMontant(montantApres);
        return this.retraitRepository.save(retrait);
    }

    @Override
    public void deleteRetrait(Long idRetrait) {
        Retrait retrait = retraitRepository.findById(idRetrait).orElseThrow(() -> new ObjectNotFoundException("Retrait not found with id : " + idRetrait));
        Double montant = retrait.getMontant() + retrait.getCommission();
        updateCaisse("+", montant.intValue());
        updateClient(retrait.getClient().getIdclient().longValue(), montant, "+");
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
        Client clientDto = clientRepository.findById(idClient.intValue()).get();
        if (signe.equals("+")) {
            clientDto.setSolde(clientDto.getSolde() + montant.intValue());
        } else {
            clientDto.setSolde(clientDto.getSolde() - montant.intValue());
        }
        return clientRepository.save(clientDto);
    }
}
