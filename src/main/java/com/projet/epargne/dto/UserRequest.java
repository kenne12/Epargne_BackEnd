package com.projet.epargne.dto;

import com.projet.epargne.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private Integer id;
    private String userName;
    private String password;
    private Collection<Role> roles;
}
