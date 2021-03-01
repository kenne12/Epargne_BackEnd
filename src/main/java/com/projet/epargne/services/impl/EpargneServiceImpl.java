package com.projet.epargne.services.impl;

import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dao.RetraitRepository;
import com.projet.epargne.dao.VersementRepository;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.entities.Client;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.services.interfaces.EpargneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EpargneServiceImpl implements EpargneService {

    @Autowired
    private CaisseRepository caisseRepository;

    @Autowired
    private VersementRepository versementRepository;

    @Autowired
    private RetraitRepository retraitRepository;


    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Versement saveVersement(Versement versement) {
        this.updateCaisse("+", (int) versement.getMontant());
        Client c = this.updateClient(versement.getClient().getIdclient().longValue(), versement.getMontant(), "+");
        versement.setSolde(c.getSolde());

        Long id = versementRepository.nextValue();
        versement.setIdVersement(id == null ? 1L : id + 1);
        return versementRepository.save(versement);

    }

    @Override
    public Versement editVersement(Versement versement) {
        Versement vAvant = versementRepository.findById(versement.getIdVersement()).get();
        this.updateCaisse("-", (int) vAvant.getMontant());
        this.updateClient(vAvant.getClient().getIdclient().longValue(), vAvant.getMontant(), "-");

        this.updateCaisse("+", (int) versement.getMontant());
        Client c = this.updateClient(versement.getClient().getIdclient().longValue(), versement.getMontant(), "-");

        versement.setSolde((double) c.getSolde());
        return versementRepository.save(versement);
    }

    @Override
    public void deleteVersement(Long idVersement) {
        Versement versement = versementRepository.findById(idVersement).get();
        this.updateCaisse("-", (int) versement.getMontant());
        this.updateClient(versement.getClient().getIdclient().longValue(), versement.getMontant(), "-");
        versementRepository.deleteById(idVersement);
    }

    @Override
    public Retrait saveRetrait(Retrait retrait) {
        Double montant = retrait.getMontant() + retrait.getCommission();
        updateCaisse("-", montant.intValue());
        Client c = this.updateClient(retrait.getClient().getIdclient().longValue(), retrait.getMontant(), "-");
        retrait.setSolde((double) c.getSolde());
        Long nexId = retraitRepository.nextValue();
        retrait.setIdRetrait(nexId == null ? 1L : nexId + 1);
        return retraitRepository.save(retrait);
    }

    @Override
    public Retrait editRetrait(Retrait retrait) {
        Retrait rAvant = retraitRepository.findById(retrait.getIdRetrait()).get();
        Double montantAvant = rAvant.getMontant() + rAvant.getCommission();
        this.updateCaisse("+", montantAvant.intValue());

        Double montantApres = retrait.getMontant() + retrait.getCommission();
        this.updateCaisse("-", montantApres.intValue());
        return this.retraitRepository.save(retrait);
    }

    @Override
    public void deleteRetrait(Long idRetrait) {
        Retrait retrait = retraitRepository.findById(idRetrait).get();
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
