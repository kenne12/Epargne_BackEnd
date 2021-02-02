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
public class Mouchard implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "idmouchard")
    private Long idMouchard;
    @Column(length = 300)
    private String action;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date heure;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
}
