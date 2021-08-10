package com.projet.epargne.dao;

import com.projet.epargne.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select max (c.idclient) from  Client  c")
    Integer nexId();

    @Query("select c from Client c WHERE c.etat=:etat order by c.numeroCarnet")
    List<Client> findByEtat(@Param("etat") boolean etat);

    @Query("select c from Client c order by c.numeroCarnet")
    List<Client> findAllOrOrderByNumeroCarnet();

    @Query("select c from Client c WHERE c.nom like :keyword or c.prenom like :keyword order by c.numeroCarnet")
    List<Client> findByNomLikeOrPrenomLike(@Param("keyword") String keyword);

    @Query("select c from Client c WHERE c.solde = 0  order by c.numeroCarnet")
    List<Client> findBySoldeEqualsZero();

    @Query("select c from Client c WHERE c.solde > 0  order by c.numeroCarnet")
    List<Client> findBySoldeGthZero();
}
