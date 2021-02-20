package com.projet.epargne.dao;

import com.projet.epargne.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select max (c.idclient) from  Client  c")
    public Integer nexId();

    @Query("select c from Client c WHERE c.etat=:etat order by c.numeroCarnet")
    public List<Client> findByEtat(@Param("etat") boolean etat);
}
