package com.projet.epargne.dao;

import com.projet.epargne.entities.Mouchard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouchardRepository extends JpaRepository<Mouchard, Long> {
}
