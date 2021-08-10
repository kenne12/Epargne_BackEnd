package com.projet.epargne.dao;

import com.projet.epargne.entities.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VersementRepository extends JpaRepository<Versement, Long> {

    @Query("select max(v.idVersement)  from Versement v")
    Long nextValue();

    @Query("select v from Versement  v where v.client.idclient=:idClient")
    List<Versement> findByIdClient(@Param("idClient") int idClient);

    @Query("select v from Versement  v where v.client.idclient=:idClient and v.date between :dateDebut and :dateFin")
    List<Versement> findByIdClientIntervalDate(@Param("idClient") int idClient, @Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);

    @Query("select v from Versement  v where v.date between :dateDebut and :dateFin")
    List<Versement> findByIntervalDate(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}
