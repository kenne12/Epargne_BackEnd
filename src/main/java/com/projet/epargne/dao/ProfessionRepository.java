package com.projet.epargne.dao;

import com.projet.epargne.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
}
