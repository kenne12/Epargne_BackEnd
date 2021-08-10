package com.projet.epargne.dao;

import com.projet.epargne.entities.Mouchard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MouchardRepository extends JpaRepository<Mouchard, Long> {

    @Query("select max(m.idMouchard)  from Mouchard m")
    Long nextValue();
}
