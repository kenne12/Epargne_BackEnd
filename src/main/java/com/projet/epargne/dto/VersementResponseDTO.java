package com.projet.epargne.dto;

import com.projet.epargne.entities.Client;
import lombok.Data;

import java.util.Date;
@Data
public class VersementResponseDTO {
    private Long idVersement;
    private double montant;
    private Date date;
    private Date heure;
    private double solde;
    private Client client;
}
