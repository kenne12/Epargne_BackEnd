package com.projet.epargne.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Retrait implements Serializable {
    @Id
    @Basic
    @Column(name = "idretrait")
    private Long idRetrait;
    private double montant;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date heure;
    private int commission;
    @Column(name = "commission_auto")
    private boolean commissionAuto;
    private double solde;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @JoinColumn(name = "id_annee_mois", referencedColumnName = "id_annee_mois")
    @ManyToOne(fetch = FetchType.LAZY)
    private AnneeMois mois;
}
