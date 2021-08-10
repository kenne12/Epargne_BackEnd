package com.projet.epargne.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaisseRequestDTO {
    private Integer idcaisse;
    private double montant;
}
