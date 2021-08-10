package com.projet.epargne.mapper;

import com.projet.epargne.dto.ClientRequestDTO;
import com.projet.epargne.dto.ClientResponseDTO;
import com.projet.epargne.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientRequestDTO entityToDto(Client client);

    Client dtoToEntity(ClientRequestDTO dto);

    ClientResponseDTO fromClientToClientResponseDTO(Client client);

    Client fromClientResponseDtoToClient(ClientResponseDTO clientResponseDTO);
}
