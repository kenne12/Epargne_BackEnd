package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.entities.Client;

public interface ClientService extends GenericService<ClientDto> {

    Integer nextValue();

    public Iterable<ClientDto> findByNonOrPrenom(String nom);

    public Iterable<ClientDto> findByEtat(boolean etat);

    public Iterable<ClientDto> findBySoldeZero();

    public Iterable<ClientDto> findBySoldeGthZero();

    public ClientDto changeState(ClientDto clientDto);
}
