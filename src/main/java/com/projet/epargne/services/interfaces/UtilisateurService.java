package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.UtilisateurRequestDTO;
import com.projet.epargne.dto.UtilisateurResponseDTO;
import org.springframework.data.domain.Page;

public interface UtilisateurService {

    Iterable<UtilisateurResponseDTO> getAll();

    Page<UtilisateurResponseDTO> getAll(int page, int size);

    UtilisateurResponseDTO findById(Long id);

    UtilisateurResponseDTO save(UtilisateurRequestDTO utilisateurRequestDTO);

    UtilisateurResponseDTO edit(UtilisateurResponseDTO dto);

    Iterable<UtilisateurResponseDTO> findByEtat(boolean etat);

    void deleteById(int id);

    UtilisateurResponseDTO findByUserName(String userName);
}
