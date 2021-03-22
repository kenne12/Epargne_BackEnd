package com.projet.epargne.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AnneeDto {
    private Integer idannee;
    private String nom;
    private boolean etat;
    private Date dateDebut;
    private Date dateFin;
}
