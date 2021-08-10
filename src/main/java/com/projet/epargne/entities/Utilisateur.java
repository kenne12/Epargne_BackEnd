package com.projet.epargne.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "idutilisateur")
    private Integer idUtilisateur;
    @Column(length = 100, nullable = false)
    private String nom;
    @Column(length = 100)
    private String prenom;
    @Column(length = 100 , name = "login")
    private String username;
    @Column(length = 300)
    private String password;
    private String photo;
    private boolean actif;
    private Instant dateCreation;
    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Privilege> privileges;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
