package com.projet.epargne.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetraitRequestDTO {
    private Long idRetrait;
    private double montant;
    private Date date;
    private Date heure;
    private int commission;
    private boolean commissionAuto;
    private int idclient;
}
