package com.projet.epargne;

import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.dto.VersementDto;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.mapper.ClientMapper;
import com.projet.epargne.services.interfaces.ClientService;
import com.projet.epargne.services.interfaces.EpargneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class EpargneApplication implements CommandLineRunner {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CaisseRepository caisseRepository;

    @Autowired
    private EpargneService epargneService;

    public static void main(String[] args) {
        SpringApplication.run(EpargneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Caisse caisse = new Caisse();
        caisse.setIdcaisse(1);
        caisse.setMontant(0d);
        caisseRepository.save(caisse);

        for (int i = 0; i < 2; i++) {
            clientService.save(new ClientDto(null, "Client nom " + i, "Client prenom " + i, "CNI N° " + i, "+237 673564186", 1500, "Informaticien", true, 0, (i + 1), null, null));
        }

        for (ClientDto c : clientService.getAll()) {
            for (int i = 0; i < 2; i++) {
                VersementDto versementDto = new VersementDto();
                versementDto.setIdVersement(null);
                versementDto.setMontant(100);
                versementDto.setClient(ClientMapper.INSTANCE.dtoToEntity(c));
                versementDto.setDate(new Date());
                versementDto.setHeure(new Date());
                epargneService.saveVersement(versementDto);
            }

            for (int i = 0; i < 2; i++) {
                RetraitDto retraitDto = new RetraitDto();
                retraitDto.setIdRetrait(null);
                retraitDto.setMontant(50);
                retraitDto.setClient(ClientMapper.INSTANCE.dtoToEntity(c));
                retraitDto.setDate(new Date());
                retraitDto.setCommission(10);
                retraitDto.setCommissionAuto(false);
                retraitDto.setHeure(new Date());
                epargneService.saveRetrait(retraitDto);
            }
        }
    }
}
