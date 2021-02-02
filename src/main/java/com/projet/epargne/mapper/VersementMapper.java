package com.projet.epargne.mapper;

import com.projet.epargne.dto.VersementDto;
import com.projet.epargne.entities.Versement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VersementMapper {
    public VersementMapper INSTANCE = Mappers.getMapper(VersementMapper.class);

    public VersementDto entityToDto(Versement versement);

    public Versement dtoToEntity(VersementDto versementDto);
}
