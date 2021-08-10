package com.projet.epargne.mapper;

import com.projet.epargne.dto.CaisseDto;
import com.projet.epargne.dto.CaisseRequestDTO;
import com.projet.epargne.dto.CaisseResponseDTO;
import com.projet.epargne.entities.Caisse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CaisseMapper {
    CaisseDto entityToDto(Caisse caisse);

    Caisse dtoToEntity(CaisseRequestDTO caisseRequestDTO);

    CaisseResponseDTO fromCaisseToCaisseResponseDto(Caisse caisse);
}
