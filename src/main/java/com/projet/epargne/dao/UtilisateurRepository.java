package com.projet.epargne.dao;

import com.projet.epargne.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("select max(u.idUtilisateur)  from Utilisateur u")
    Integer nextValue();

    @Query("select u from Utilisateur  u where u.actif=:actif")
    List<Utilisateur> findAllByActif(@Param("actif") boolean etat);

    @Query("select u from Utilisateur  u where u.username=:username")
    Utilisateur findByUsername(@Param("username") String username);

    //Utilisateur findByUsername(String username);
}
