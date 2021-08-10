package com.projet.epargne.dto;

import com.projet.epargne.entities.Privilege;
import lombok.Data;

import java.time.Instant;
import java.util.Collection;

@Data
public class UtilisateurResponseDTO {
    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String photo;
    private boolean actif;
    private Instant dateCreation;
    private Collection<Privilege> privileges;
}
