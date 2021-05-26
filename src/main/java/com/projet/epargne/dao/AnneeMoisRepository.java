package com.projet.epargne.dao;

import com.projet.epargne.entities.AnneeMois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnneeMoisRepository extends JpaRepository<AnneeMois, Integer> {

    @Query("select max(a.id)  from AnneeMois a")
    public Integer nextValue();

    @Query("select a from AnneeMois a order by a.dateDebut desc")
    List<AnneeMois> findAllOrder();

    @Query("select a from AnneeMois a WHERE a.annee.idannee=:idannee order by a.mois.numero")
    List<AnneeMois> findByIdAnnee(@Param("idannee") int idAnnee);

    @Query("select a from AnneeMois a WHERE a.annee.idannee=:idannee AND a.etat =:etat order by a.mois.numero")
    List<AnneeMois> findByIdAnneeEtat(@Param("idannee") int idAnnee , @Param("etat") boolean etat);

    @Query("select a from AnneeMois a WHERE  a.etat =:etat order by a.mois.numero")
    List<AnneeMois> findByEtat(@Param("etat") boolean etat);
}
