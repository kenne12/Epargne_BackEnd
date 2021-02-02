package com.projet.epargne.dao;

import com.projet.epargne.entities.Versement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersementRepository extends JpaRepository<Versement, Long> {
}
