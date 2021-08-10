package com.projet.epargne.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessionRequestDTO {
    private Integer idProfession;
    private String nom;
}
