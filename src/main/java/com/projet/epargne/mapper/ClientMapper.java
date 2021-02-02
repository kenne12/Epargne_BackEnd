package com.projet.epargne.mapper;

import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    public ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public ClientDto entityToDto(Client client);

    public Client dtoToEntity(ClientDto dto);
}
