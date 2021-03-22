package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.AnneeMoisDto;
import com.projet.epargne.entities.AnneeMois;

import java.util.List;

public interface AnneeMoisService extends GenericService<AnneeMoisDto> {

    List<AnneeMoisDto> findByIdAnne(int idAnnee);
}
