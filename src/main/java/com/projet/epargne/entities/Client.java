package com.projet.epargne.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @Column(name = "numerocarnet", nullable = false, unique = true)
    private int numeroCarnet;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Retrait> retraits;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Versement> versements;
}
