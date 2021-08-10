package com.projet.epargne.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeDto {
    private Integer idannee;
    private String nom;
    private boolean etat;
    private Date dateDebut;
    private Date dateFin;
}
