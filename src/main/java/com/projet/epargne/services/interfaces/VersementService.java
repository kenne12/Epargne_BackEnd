package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.VersementRequestDTO;
import com.projet.epargne.dto.VersementResponseDTO;
import com.projet.epargne.entities.Versement;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface VersementService {

    Page<Versement> getAll(int page, int size);

    VersementResponseDTO findById(Long id);

    VersementResponseDTO save(VersementRequestDTO versementRequestDTO);

    VersementResponseDTO edit(VersementRequestDTO versementRequestDTO);

    void deleteById(Long id);

    Iterable<VersementResponseDTO> findByIdClientBetwenToDates(int idClient, Date dateDebut, Date dateFin);

    Iterable<VersementResponseDTO> findByIntervalDate(Date dateDebut, Date dateFin);
}
