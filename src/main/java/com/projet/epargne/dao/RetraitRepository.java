package com.projet.epargne.dao;

import com.projet.epargne.entities.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RetraitRepository extends JpaRepository<Retrait, Long> {

    @Query("select max(r.idRetrait)  from Retrait r")
    public Long nextValue();
}
