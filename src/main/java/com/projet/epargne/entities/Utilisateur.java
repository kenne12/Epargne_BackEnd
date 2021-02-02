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
public class Utilisateur implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "idutilisateur")
    private Integer idUtilisateur;
    @Column(length = 100, nullable = false)
    private String nom;
    @Column(length = 100)
    private String prenom;
    @Column(length = 100)
    private String login;
    @Column(length = 100)
    private String password;
    private String photo;
    private boolean actif;
}
