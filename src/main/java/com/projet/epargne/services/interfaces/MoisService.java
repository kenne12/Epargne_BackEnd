package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.MoisDto;

public interface MoisService {

    Iterable<MoisDto> getAll();

    MoisDto findById(int id);

    MoisDto save(MoisDto dto);

    MoisDto edit(MoisDto dto);

    void deleteById(int id);
}
