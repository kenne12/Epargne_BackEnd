package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.RetraitDto;

public interface RetraitService extends GenericService<RetraitDto , RetraitDto> {

    public Long nextValue();
}
