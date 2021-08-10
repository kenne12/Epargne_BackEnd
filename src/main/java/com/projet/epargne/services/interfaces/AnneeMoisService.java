package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.AnneeMoisDto;

import java.util.List;

public interface AnneeMoisService extends GenericService<AnneeMoisDto , AnneeMoisDto> {

    List<AnneeMoisDto> findByIdAnne(int idAnnee);

    List<AnneeMoisDto> findByEtat(boolean etat);
}
