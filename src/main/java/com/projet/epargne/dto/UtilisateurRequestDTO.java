package com.projet.epargne.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurRequestDTO {
    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String repeatPassword;
    private boolean actif;
    private Instant dateCreation;
}
