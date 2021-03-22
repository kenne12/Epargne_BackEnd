package com.projet.epargne.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
