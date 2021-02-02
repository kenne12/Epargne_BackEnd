package com.projet.epargne.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege implements Serializable {

    @Id
    @Basic
    @Column(name = "idprivilege")
    private Long idPrivilege;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
}
