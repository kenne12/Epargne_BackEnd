package com.projet.epargne.mapper;

import com.projet.epargne.dto.ProfessionDto;
import com.projet.epargne.entities.Profession;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfessionMapper {

    ProfessionMapper INSTANCE = Mappers.getMapper(ProfessionMapper.class);

    public ProfessionDto entityToDto(Profession profession);

    public Profession dtoToEntity(ProfessionDto dto);
}
