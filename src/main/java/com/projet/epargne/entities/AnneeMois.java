package com.projet.epargne.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "annee_mois")
public class AnneeMois implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "id_annee_mois")
    private Integer id;
    @Column(name = "date_debut" , nullable = false)
    private Date dateDebut;
    @Column(name = "date_fin" , nullable = false)
    private Date dateFin;
    private boolean etat;
    @JoinColumn(name = "idmois", referencedColumnName = "idmois")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mois mois;

    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee annee;
}
