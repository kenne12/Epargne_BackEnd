package com.projet.epargne.dao;

import com.projet.epargne.entities.Mois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoisRepository extends JpaRepository<Mois, Integer> {

    @Query("select max(m.idmois)  from Mois m")
    Integer nextValue();

    @Query("select m from Mois m order by m.numero")
    List<Mois> findAllOrder();
}
