package com.projet.epargne.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {
    @Id
    @Basic(optional = false)
    private Integer idclient;
    @Column(length = 50, nullable = false)
    private String nom;
    @Column(length = 50)
    private String prenom;
    private String cni;
    private String contact;
    private int solde;
    private String profession;
    private boolean etat;
    @Column(name = "fraiscarnet")
    private int fraisCarnet;
    @Column(name = "numerocarnet", nullable = false)
    private int numeroCarnet;
}
