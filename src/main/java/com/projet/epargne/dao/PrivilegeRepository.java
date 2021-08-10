package com.projet.epargne.dao;

import com.projet.epargne.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Query("select max(p.idPrivilege)  from Privilege p")
    Long nextValue();

    @Query("select  p from Privilege p where  p.utilisateur.idUtilisateur=:id")
    Collection<Privilege> findPrivilegeByIdUtilisateur(@Param("id") int idUtilisateur);
}
