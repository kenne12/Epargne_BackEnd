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
@NoArgsConstructor
@AllArgsConstructor
public class Annee implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer idannee;
    @Column(length = 30)
    private String nom;
    private boolean etat;
}
