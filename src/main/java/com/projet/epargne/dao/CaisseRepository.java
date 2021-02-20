package com.projet.epargne.dao;

import com.projet.epargne.entities.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CaisseRepository extends JpaRepository<Caisse, Integer> {
    @Query("select c from Caisse c where c.idcaisse=1")
    public Caisse defaultCaisse();
}
