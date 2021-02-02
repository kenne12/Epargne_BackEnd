package com.projet.epargne.dto;

import com.projet.epargne.entities.AnneeMois;
import com.projet.epargne.entities.Client;
import lombok.Data;

import java.util.Date;

@Data
public class RetraitDto {
    private Long idRetrait;
    private double montant;
    private Date date;
    private Date heure;
    private int commission;
    private boolean commissionAuto;
    private double solde;
    private Client client;
    private AnneeMois mois;
}
