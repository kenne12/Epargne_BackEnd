package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.VersementDto;

import java.util.Date;

public interface VersementService extends GenericService<VersementDto> {

    Long nextValue();

    Iterable<VersementDto> findByIdClientIntervalDate(int idClient, Date dateDebut, Date dateFin);

    Iterable<VersementDto> findByIntervalDate(Date dateDebut, Date dateFin);
}
