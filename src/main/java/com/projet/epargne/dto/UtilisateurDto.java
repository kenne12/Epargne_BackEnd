package com.projet.epargne.dto;

import lombok.Data;
@Data
public class UtilisateurDto {
    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String photo;
    private boolean actif;
}
