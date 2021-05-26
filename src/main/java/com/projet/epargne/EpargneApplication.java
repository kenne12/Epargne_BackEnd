package com.projet.epargne;

import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dao.ProfessionRepository;
import com.projet.epargne.dto.*;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.entities.Profession;
import com.projet.epargne.mapper.MenuMapper;
import com.projet.epargne.mapper.UtilisateurMapper;
import com.projet.epargne.services.impl.EpargneServiceImpl;
import com.projet.epargne.services.interfaces.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class EpargneApplication implements CommandLineRunner {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private ClientService clientService;

    @Autowired
    private CaisseRepository caisseRepository;

    @Autowired
    private EpargneServiceImpl epargneService;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private AnneeService anneeService;

    @Autowired
    private MoisService moisService;

    public static void main(String[] args) {
        SpringApplication.run(EpargneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Caisse caisse = new Caisse();
        caisse.setIdcaisse(1);
        caisse.setMontant(0d);
        caisseRepository.save(caisse);

        professionRepository.save(new Profession(1, "Informaticiel"));
        professionRepository.save(new Profession(2, "Comptable"));
        professionRepository.save(new Profession(3, "Agent Commercial"));

        for (int i = 0; i < 25; i++) {
            clientService.save(new ClientDto(0, "Client " + (i + 1), " - ", "CNI N° " + i, "+237 673564186", 1500, "Informaticien", true, 0, (i + 1), null, null));
        }

        for (ClientDto c : clientService.getAll()) {
            Random rnd = new Random();
            for (int i = 0; i < 5; i++) {
                double montant = Math.ceil((rnd.nextInt(8) * 100) + 300d);

                VersementRequest versementRequest = VersementRequest.builder()
                        .idVersement(null)
                        .date(new Date())
                        .heure(new Date())
                        .idclient(c.getIdclient())
                        .montant(montant)
                        .build();
                epargneService.saveVersement(versementRequest);
            }

            for (int i = 0; i < 2; i++) {

                double montant = Math.ceil((rnd.nextInt(10) * 5) + 75d);

                RetraitRequest retraitRequest = RetraitRequest.builder()
                        .idRetrait(null)
                        .idclient(c.getIdclient())
                        .date(new Date())
                        .heure(new Date())
                        .commission(10)
                        .commissionAuto(false)
                        .montant(montant)
                        .build();

                epargneService.saveRetrait(retraitRequest);
            }
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setIdUtilisateur(0);
        utilisateurDto.setLogin("admin");
        utilisateurDto.setPassword("admin");
        utilisateurDto.setNom("Kenne");
        utilisateurDto.setPrenom("Gervais");
        utilisateurDto.setActif(true);

        utilisateurDto = utilisateurService.save(utilisateurDto);


        MenuDto m = new MenuDto();
        m.setIdmenu(0);
        m.setRole("ADMIN");
        m.setRessource("APP-MANAGER");
        m = menuService.save(m);

        MenuDto m1 = new MenuDto();
        m1.setIdmenu(0);
        m1.setRole("USER");
        m1.setRessource("APP-USER");
        m1 = menuService.save(m1);


        PrivilegeDto p = new PrivilegeDto();
        p.setIdPrivilege(0l);
        savePrivilege(p, utilisateurDto, m);


        p = new PrivilegeDto();
        p.setIdPrivilege(0l);
        savePrivilege(p, utilisateurDto, m1);


        AnneeDto anneeDto1 = new AnneeDto();
        anneeDto1.setIdannee(0);
        anneeDto1.setNom("2019");
        anneeDto1.setEtat(true);
        anneeDto1.setDateDebut(new DateTime("2019-01-01").toDate());
        anneeDto1.setDateFin(new DateTime("2019-12-31").toDate());
        anneeService.save(anneeDto1);

        AnneeDto anneeDto2 = new AnneeDto();
        anneeDto2.setIdannee(0);
        anneeDto2.setNom("2020");
        anneeDto2.setEtat(true);
        anneeDto2.setDateDebut(new DateTime("2020-01-01").toDate());

        anneeDto2.setDateFin(new DateTime("2020-12-31").toDate());
        anneeService.save(anneeDto2);

        moisService.save(new MoisDto(0, "Janvier", 1));
        moisService.save(new MoisDto(0, "Février", 2));
        moisService.save(new MoisDto(0, "Mars", 3));
        moisService.save(new MoisDto(0, "Avril", 4));
        moisService.save(new MoisDto(0, "Mai", 5));
        moisService.save(new MoisDto(0, "Juin", 6));
        moisService.save(new MoisDto(0, "Juillet", 7));
        moisService.save(new MoisDto(0, "Aout", 8));
        moisService.save(new MoisDto(0, "Septembre", 9));
        moisService.save(new MoisDto(0, "Octobre", 10));
        moisService.save(new MoisDto(0, "Novembre", 11));
        moisService.save(new MoisDto(0, "Décembre", 12));
    }

    private void savePrivilege(PrivilegeDto p, UtilisateurDto u, MenuDto m) {
        p.setUtilisateur(UtilisateurMapper.INSTANCE.dtoToEntity(u));
        p.setMenu(MenuMapper.INSTANCE.dtoToEntity(m));
        privilegeService.save(p);
    }
}
