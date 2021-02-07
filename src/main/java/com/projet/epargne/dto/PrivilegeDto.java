package com.projet.epargne.dto;

import com.projet.epargne.entities.Menu;
import com.projet.epargne.entities.Utilisateur;
import lombok.Data;

@Data
public class PrivilegeDto {
    private Long idPrivilege;
    private Utilisateur utilisateur;
    private Menu menu;
}
