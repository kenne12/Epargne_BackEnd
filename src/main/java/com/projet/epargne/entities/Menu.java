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
public class Menu implements Serializable {
    @Id
    @Basic(optional = false)
    private Integer idmenu;
    @Column(length = 50)
    private String nom;
    @Column(length = 70)
    private String ressource;
}
