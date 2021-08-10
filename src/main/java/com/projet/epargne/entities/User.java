package com.projet.epargne.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer id;

    @Column(name = "username")
    private String username;
    private String password;
    private boolean etat;
    private String email;
    @Column(name = "creation_date")
    private Instant creationDate;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
