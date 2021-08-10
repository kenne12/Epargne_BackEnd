package com.projet.epargne.dto;

import com.projet.epargne.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetraitResponseDTO {
    private Long idRetrait;
    private double montant;
    private Date date;
    private Date heure;
    private int commission;
    private boolean commissionAuto;
    private Client client;
}
