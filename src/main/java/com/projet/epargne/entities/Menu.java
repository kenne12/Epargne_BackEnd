package com.projet.epargne.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Menu implements Serializable {
    @Id
    @Basic(optional = false)
    private Integer idmenu;
    @Column(name = "nom",length = 50)
    private String role;
    @Column(length = 70)
    private String ressource;
}
