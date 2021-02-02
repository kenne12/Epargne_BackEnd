package com.projet.epargne.dao;

import com.projet.epargne.entities.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaisseRepository extends JpaRepository<Caisse, Integer> {
}
