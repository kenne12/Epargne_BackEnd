package com.projet.epargne.mapper;

import com.projet.epargne.dto.VersementRequestDTO;
import com.projet.epargne.dto.VersementResponseDTO;
import com.projet.epargne.entities.Versement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VersementMapper {
     VersementMapper INSTANCE = Mappers.getMapper(VersementMapper.class);

     VersementResponseDTO entityToDto(Versement versement);

     Versement dtoToEntity(VersementResponseDTO versementResponseDTO);

     Versement fromRequestToEnity(VersementRequestDTO versementRequestDTO);
}
