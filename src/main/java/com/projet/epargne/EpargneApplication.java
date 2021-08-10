package com.projet.epargne;

import com.projet.epargne.dto.*;
import com.projet.epargne.mapper.MenuMapper;
import com.projet.epargne.mapper.UtilisateurMapper;
import com.projet.epargne.services.interfaces.*;
import org.joda.time.DateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class EpargneApplication {

    private final PrivilegeService privilegeService;

    public EpargneApplication(PrivilegeService privilegeService) {//
        this.privilegeService = privilegeService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {//
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {//
        SpringApplication.run(EpargneApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ClientService clientService, EpargneService epargneService,
                          CaisseService caisseService, UtilisateurService utilisateurService,
                          MenuService menuService, MoisService moisService,
                          ProfessionService professionService, AnneeService anneeService) throws Exception {

        return args -> {
            caisseService.save(new CaisseRequestDTO(1, 0D));

            professionService.save(new ProfessionRequestDTO(null, "Informaticien"));
            professionService.save(new ProfessionRequestDTO(null, "Comptable"));
            professionService.save(new ProfessionRequestDTO(null, "Agent Commercial"));

            for (int i = 0; i < 50; i++) {
                clientService.save(new ClientRequestDTO(null, "Client " + (i + 1), " - ", "CNI N° " + i, "+237 673564186", 1500, "Informaticien", true, 0, (i + 1)));
            }

            for (ClientResponseDTO c : clientService.getAll()) {
                Random rnd = new Random();
                for (int i = 0; i < 5; i++) {
                    double montant = Math.ceil((rnd.nextInt(8) * 100) + 300d);

                    VersementRequestDTO versementRequestDTO = VersementRequestDTO.builder()
                            .date(new Date())
                            .heure(new Date())
                            .idclient(c.getIdclient())
                            .montant(montant)
                            .build();
                    epargneService.saveVersement(versementRequestDTO);
                }

                for (int i = 0; i < 2; i++) {

                    double montant = Math.ceil((rnd.nextInt(10) * 5) + 75d);

                    RetraitRequestDTO retraitRequestDTO = RetraitRequestDTO.builder()
                            .idclient(c.getIdclient())
                            .date(new Date())
                            .heure(new Date())
                            .commission(10)
                            .commissionAuto(false)
                            .montant(montant)
                            .build();

                    epargneService.saveRetrait(retraitRequestDTO);
                }
            }

            UtilisateurRequestDTO utilisateurRequestDTO = UtilisateurRequestDTO.builder()
                    .nom("Kenne")
                    .prenom("Gervais")
                    .username("admin")
                    .password("admin")
                    .repeatPassword("admin")
                    .dateCreation((Instant.now()))
                    .actif(true)
                    .build();

            UtilisateurResponseDTO utilisateurResponseDTO = utilisateurService.save(utilisateurRequestDTO);

            MenuRequestDTO m = new MenuRequestDTO();
            m.setRole("ADMIN");
            m.setRessource("APP-MANAGER");

            m = menuService.save(m);

            MenuRequestDTO m1 = new MenuRequestDTO();
            m1.setRole("USER");
            m1.setRessource("APP-USER");

            m1 = menuService.save(m1);

            PrivilegeDto p = new PrivilegeDto();
            savePrivilege(p, utilisateurResponseDTO, m);

            p = new PrivilegeDto();
            savePrivilege(p, utilisateurResponseDTO, m1);

            AnneeDto anneeDto1 = new AnneeDto();
            anneeDto1.setNom("2019");
            anneeDto1.setEtat(true);
            anneeDto1.setDateDebut(new DateTime("2019-01-01").toDate());
            anneeDto1.setDateFin(new DateTime("2019-12-31").toDate());
            anneeService.save(anneeDto1);

            AnneeDto anneeDto2 = new AnneeDto();
            anneeDto2.setNom("2020");
            anneeDto2.setEtat(true);
            anneeDto2.setDateDebut(new DateTime("2020-01-01").toDate());
            anneeDto2.setDateFin(new DateTime("2020-12-31").toDate());
            anneeService.save(anneeDto2);

            moisService.save(new MoisDto(null, "Janvier", 1));
            moisService.save(new MoisDto(null, "Février", 2));
            moisService.save(new MoisDto(null, "Mars", 3));
            moisService.save(new MoisDto(null, "Avril", 4));
            moisService.save(new MoisDto(null, "Mai", 5));
            moisService.save(new MoisDto(null, "Juin", 6));
            moisService.save(new MoisDto(null, "Juillet", 7));
            moisService.save(new MoisDto(null, "Aout", 8));
            moisService.save(new MoisDto(null, "Septembre", 9));
            moisService.save(new MoisDto(null, "Octobre", 10));
            moisService.save(new MoisDto(null, "Novembre", 11));
            moisService.save(new MoisDto(null, "Décembre", 12));
        };
    }

    private void savePrivilege(PrivilegeDto p, UtilisateurResponseDTO u, MenuRequestDTO m) {
        p.setUtilisateur(UtilisateurMapper.INSTANCE.dtoToEntity(u));
        p.setMenu(MenuMapper.INSTANCE.dtoToEntity(m));
        privilegeService.save(p);
    }
}
