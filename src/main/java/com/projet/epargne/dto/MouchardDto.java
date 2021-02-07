package com.projet.epargne.dto;

import com.projet.epargne.entities.Utilisateur;
import lombok.Data;

import java.util.Date;

@Data
public class MouchardDto {
    private Long idMouchard;
    private String action;
    private Date date;
    private Date heure;
    private Utilisateur utilisateur;
}
