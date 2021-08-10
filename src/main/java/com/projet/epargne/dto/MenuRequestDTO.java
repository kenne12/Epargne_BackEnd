package com.projet.epargne.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDTO {
    private Integer idmenu;
    private String role;
    private String ressource;
}
