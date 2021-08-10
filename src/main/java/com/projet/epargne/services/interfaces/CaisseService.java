package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.CaisseRequestDTO;
import com.projet.epargne.dto.CaisseResponseDTO;

public interface CaisseService {

     CaisseResponseDTO save(CaisseRequestDTO caisseRequestDTO);
}
