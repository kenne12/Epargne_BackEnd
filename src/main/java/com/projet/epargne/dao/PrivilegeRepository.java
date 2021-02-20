package com.projet.epargne.dao;

import com.projet.epargne.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Query("select max(p.idPrivilege)  from Privilege p")
    public Long nextValue();
}
