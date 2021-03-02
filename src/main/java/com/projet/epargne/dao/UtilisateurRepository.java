package com.projet.epargne.dao;

import com.projet.epargne.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("select max(u.idUtilisateur)  from Utilisateur u")
    public Integer nextValue();

    @Query("select u from Utilisateur  u where u.actif=:actif")
    public List<Utilisateur> findAllByActif(@Param("actif") boolean etat);
}
