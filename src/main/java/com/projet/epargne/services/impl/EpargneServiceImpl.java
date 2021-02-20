package com.projet.epargne.services.impl;

import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.dto.VersementDto;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.services.interfaces.ClientService;
import com.projet.epargne.services.interfaces.EpargneService;
import com.projet.epargne.services.interfaces.RetraitService;
import com.projet.epargne.services.interfaces.VersementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EpargneServiceImpl implements EpargneService {

    @Autowired
    private CaisseRepository caisseRepository;

    @Autowired
    private VersementService versementService;

    @Autowired
    private RetraitService retraitService;

    @Autowired
    private ClientService clientService;

    @Override
    public VersementDto saveVersement(VersementDto versementDto) {
        this.updateCaisse("+", (int) versementDto.getMontant());
        ClientDto c = this.updateClient(versementDto.getClient().getIdclient().longValue(), versementDto.getMontant(), "+");
        versementDto.setMontant(c.getSolde());
        versementDto.setIdVersement(versementService.nextValue());
        return versementService.save(versementDto);
    }

    @Override
    public VersementDto editVersement(VersementDto versementDto) {
        VersementDto vAvant = versementService.findById(versementDto.getIdVersement());
        this.updateCaisse("-", (int) vAvant.getMontant());
        this.updateClient(versementDto.getClient().getIdclient().longValue(), vAvant.getMontant(), "-");

        this.updateCaisse("+", (int) versementDto.getMontant());
        ClientDto c = this.updateClient(versementDto.getClient().getIdclient().longValue(), versementDto.getMontant(), "-");

        versementDto.setSolde((double) c.getSolde());
        return versementService.edit(versementDto);
    }

    @Override
    public void deleteVersement(VersementDto versementDto) {
        this.updateCaisse("-", (int) versementDto.getMontant());
        this.updateClient(versementDto.getClient().getIdclient().longValue(), versementDto.getMontant(), "-");
    }

    @Override
    public RetraitDto saveRetrait(RetraitDto retraitDto) {
        Double montant = retraitDto.getMontant() + retraitDto.getCommission();
        updateCaisse("-", montant.intValue());
        ClientDto c = this.updateClient(retraitDto.getClient().getIdclient().longValue(), retraitDto.getMontant(), "-");
        retraitDto.setSolde((double) c.getSolde());
        retraitDto.setIdRetrait(retraitService.nextValue());
        return retraitService.save(retraitDto);
    }

    @Override
    public RetraitDto editRetrait(RetraitDto retraitDto) {
        RetraitDto rAvant = retraitService.findById(retraitDto.getIdRetrait());
        Double montantAvant = rAvant.getMontant() + rAvant.getCommission();
        this.updateCaisse("+", montantAvant.intValue());


        Double montantApres = retraitDto.getMontant() + retraitDto.getCommission();
        this.updateCaisse("-", montantApres.intValue());

        return this.retraitService.edit(retraitDto);
    }

    @Override
    public void deleteRetrait(RetraitDto retraitDto) {
        Double montant = retraitDto.getMontant() + retraitDto.getCommission();
        updateCaisse("+", montant.intValue());
        updateClient(retraitDto.getClient().getIdclient().longValue(), montant, "+");
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

    private ClientDto updateClient(Long idClient, Double montant, String signe) {
        ClientDto clientDto = clientService.findById(idClient);

        if (signe.equals("+")) {
            clientDto.setSolde(clientDto.getSolde() + montant.intValue());
        } else {
            clientDto.setSolde(clientDto.getSolde() - montant.intValue());
        }
        return clientService.edit(clientDto);
    }
}
