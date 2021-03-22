package com.projet.epargne;

import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dao.ProfessionRepository;
import com.projet.epargne.dto.*;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.entities.Profession;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.mapper.ClientMapper;
import com.projet.epargne.mapper.MenuMapper;
import com.projet.epargne.mapper.UtilisateurMapper;
import com.projet.epargne.services.interfaces.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

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
    private EpargneService epargneService;

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

        for (int i = 0; i < 20; i++) {
            clientService.save(new ClientDto(0, "Client " + i, " - ", "CNI N° " + i, "+237 673564186", 1500, "Informaticien", true, 0, (i + 1), null, null));
        }

        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (ClientDto c : clientService.getAll()) {
            for (int i = 0; i < 2; i++) {
                Versement versementDto = new Versement();
                versementDto.setIdVersement(null);
                versementDto.setMontant(100);
                versementDto.setClient(ClientMapper.INSTANCE.dtoToEntity(c));
                versementDto.setDate(new Date());
                versementDto.setHeure(new Date());
                epargneService.saveVersement(versementDto);
            }

            for (int i = 0; i < 2; i++) {
                Retrait retraitDto = new Retrait();
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
