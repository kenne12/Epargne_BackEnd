package com.projet.epargne.dto;

import lombok.Data;

@Data
public class ClientDto {
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
