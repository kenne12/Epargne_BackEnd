package com.projet.epargne.mapper;

import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.dto.RetraitRequestDTO;
import com.projet.epargne.entities.Retrait;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RetraitMapper {
    RetraitMapper INSTANCE = Mappers.getMapper(RetraitMapper.class);

    RetraitDto entityToDto(Retrait retrait);

    Retrait dtoToEntity(RetraitDto dto);

    Retrait fromRequestToEntity(RetraitRequestDTO retraitRequestDTO);
}
