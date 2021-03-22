package com.projet.epargne.dao;

import com.projet.epargne.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select max (r.id) from Role r")
    public Integer nextValue();
}
