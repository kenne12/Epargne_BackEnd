package com.projet.epargne.dao;

import com.projet.epargne.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

    @Query("select max(p.idProfession)  from Profession p")
    public Integer nextValue();
}
