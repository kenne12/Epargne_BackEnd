package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.ClientDto;

public interface ClientService extends GenericService<ClientDto> {

    Integer nextValue();
}
