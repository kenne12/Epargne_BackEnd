package com.projet.epargne.dto;

import com.projet.epargne.entities.Annee;
import com.projet.epargne.entities.Mois;
import lombok.Data;

import java.util.Date;

@Data
public class AnneeMoisDto {
    private Integer id;
    private Date dateDebut;
    private Date dateFin;
    private boolean etat;
    private Mois mois;
    private Annee annee;
}
