package com.projet.epargne.dao;

import com.projet.epargne.entities.Mois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoisRepository extends JpaRepository<Mois, Integer> {

    @Query("select max(m.idmois)  from Mois m")
    public Integer nextValue();
}
