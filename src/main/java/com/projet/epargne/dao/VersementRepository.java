package com.projet.epargne.dao;

import com.projet.epargne.entities.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VersementRepository extends JpaRepository<Versement, Long> {

    @Query("select max(v.idVersement)  from Versement v")
    public Long nextValue();
}
