package com.projet.epargne.dao;

import com.projet.epargne.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
