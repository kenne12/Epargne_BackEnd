package com.projet.epargne.dao;

import com.projet.epargne.entities.Annee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeRepository extends JpaRepository<Annee, Integer> {
}
