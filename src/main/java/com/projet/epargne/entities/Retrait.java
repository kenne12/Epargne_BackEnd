package com.projet.epargne.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private Date date;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = true)
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
