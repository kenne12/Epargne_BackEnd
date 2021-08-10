package com.projet.epargne.dao;

import com.projet.epargne.entities.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetraitRepository extends JpaRepository<Retrait, Long> {

    @Query("select max(r.idRetrait)  from Retrait r")
    Long nextValue();

    @Query("select r from Retrait  r where r.client.idclient=:idClient")
    List<Retrait> findByIdClient(@Param("idClient") int idClient);
}
