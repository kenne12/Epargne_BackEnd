package com.projet.epargne.dao;

import com.projet.epargne.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("select max(u.idUtilisateur)  from Utilisateur u")
    public Integer nextValue();
}
