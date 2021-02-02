package com.projet.epargne.dao;

import com.projet.epargne.entities.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetraitRepository extends JpaRepository<Retrait, Long> {
}
