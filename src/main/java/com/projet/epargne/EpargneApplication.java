package com.projet.epargne;

import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpargneApplication implements CommandLineRunner {

    @Autowired
    private ClientService clientService;

    public static void main(String[] args) {
        SpringApplication.run(EpargneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
           clientService.save(new ClientDto((i + 1l), "Client nom " + i, "Client prenom " + i, "CNI N° " + i, "+237 673564186", 1500, "Informaticien", true, 0, (i + 1), null, null));
        }
    }
}
