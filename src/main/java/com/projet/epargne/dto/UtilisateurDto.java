package com.projet.epargne.dto;

import com.projet.epargne.entities.Privilege;
import lombok.Data;

import java.util.Collection;

@Data
public class UtilisateurDto {
    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String photo;
    private boolean actif;
    private Collection<Privilege> privileges;
}
