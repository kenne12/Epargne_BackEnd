package com.projet.epargne.dao;

import com.projet.epargne.entities.Annee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnneeRepository extends JpaRepository<Annee, Integer> {

    @Query("select max(a.idannee) from Annee a")
    public Integer nextValue();

    @Query("select a from Annee a ORDER BY a.dateDebut")
    List<Annee> findAllOrder();

    @Query("select a from Annee a where a.etat=:etat ORDER BY a.dateDebut")
    List<Annee> findByEtat(@Param("etat") boolean etat);

}
