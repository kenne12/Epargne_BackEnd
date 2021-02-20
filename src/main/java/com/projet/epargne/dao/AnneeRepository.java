package com.projet.epargne.dao;

import com.projet.epargne.entities.Annee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnneeRepository extends JpaRepository<Annee, Integer> {

    @Query("select max(a.idannee)  from Annee a")
    public Integer nextValue();
}
