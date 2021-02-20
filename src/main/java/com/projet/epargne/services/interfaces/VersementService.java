package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.VersementDto;

public interface VersementService extends GenericService<VersementDto> {

    Long nextValue();
}
