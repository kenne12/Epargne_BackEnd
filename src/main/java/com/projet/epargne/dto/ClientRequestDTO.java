package com.projet.epargne.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientRequestDTO {
    private Integer idclient;
    private String nom;
    private String prenom;
    private String cni;
    private String contact;
    private int solde;
    private String profession;
    private boolean etat;
    private int fraisCarnet;
    private int numeroCarnet;
}
