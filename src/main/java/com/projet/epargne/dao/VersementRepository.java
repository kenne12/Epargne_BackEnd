package com.projet.epargne.dao;

import com.projet.epargne.entities.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VersementRepository extends JpaRepository<Versement, Long> {

    @Query("select max(v.idVersement)  from Versement v")
    public Long nextValue();

    @Query("select v from Versement  v where v.client.idclient=:idClient")
    public List<Versement> findByIdClient(@Param("idClient") int idClient);
}
