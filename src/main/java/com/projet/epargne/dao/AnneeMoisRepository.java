package com.projet.epargne.dao;

import com.projet.epargne.entities.AnneeMois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnneeMoisRepository extends JpaRepository<AnneeMois, Integer> {

    @Query("select max(a.id)  from AnneeMois a")
    public Integer nextValue();
}
