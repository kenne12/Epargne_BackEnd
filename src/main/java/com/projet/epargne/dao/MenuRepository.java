package com.projet.epargne.dao;

import com.projet.epargne.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("select max(m.idmenu)  from Menu m")
    Integer nextValue();
}
