package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.ProfessionRequestDTO;
import com.projet.epargne.dto.ProfessionResponseDTO;

public interface ProfessionService {

    Iterable<ProfessionResponseDTO> getAll();

    ProfessionResponseDTO findById(int id);

    ProfessionResponseDTO save(ProfessionRequestDTO dto);

    ProfessionResponseDTO edit(ProfessionRequestDTO dto);

    void deleteById(int id);

}
