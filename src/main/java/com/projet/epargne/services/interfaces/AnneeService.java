package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.AnneeDto;
import org.springframework.stereotype.Service;

@Service
public interface AnneeService extends GenericService<AnneeDto> {

    Iterable<AnneeDto> findByEtat(boolean etat);
}
