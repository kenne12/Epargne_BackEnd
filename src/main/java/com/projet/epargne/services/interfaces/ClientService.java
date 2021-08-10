package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.ClientRequestDTO;
import com.projet.epargne.dto.ClientResponseDTO;

public interface ClientService {

    Iterable<ClientResponseDTO> getAll();

    ClientResponseDTO findById(int id);

    ClientResponseDTO save(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO edit(ClientResponseDTO clientResponseDTO);

    void deleteById(int id);

    Iterable<ClientResponseDTO> findByNonOrPrenom(String nom);

    Iterable<ClientResponseDTO> findByEtat(boolean etat);

    Iterable<ClientResponseDTO> findBySoldeZero();

    Iterable<ClientResponseDTO> findBySoldeGthZero();

    ClientResponseDTO changeState(int idClient, boolean state);
}
