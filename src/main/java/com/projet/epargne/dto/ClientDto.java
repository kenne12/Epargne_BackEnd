package com.projet.epargne.dto;

import com.projet.epargne.entities.Retrait;
import com.projet.epargne.entities.Versement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
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
    private Collection<Retrait> retraits;
    private Collection<Versement> versements;
}
