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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profession implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "idpression")
    private Integer idProfession;
    @Column(length = 30)
    private String nom;
}
