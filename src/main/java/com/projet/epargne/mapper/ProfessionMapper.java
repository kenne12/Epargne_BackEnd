package com.projet.epargne.mapper;

import com.projet.epargne.dto.ProfessionRequestDTO;
import com.projet.epargne.dto.ProfessionResponseDTO;
import com.projet.epargne.entities.Profession;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessionMapper {

    public ProfessionRequestDTO entityToDto(Profession profession);

    public Profession fromRequestDtoToEnity(ProfessionRequestDTO dto);

    public ProfessionResponseDTO fromEntityToResponseDto(Profession profession);
}
