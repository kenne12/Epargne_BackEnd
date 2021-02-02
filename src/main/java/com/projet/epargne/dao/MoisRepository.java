package com.projet.epargne.dao;

import com.projet.epargne.entities.Mois;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoisRepository extends JpaRepository<Mois, Integer> {
}
