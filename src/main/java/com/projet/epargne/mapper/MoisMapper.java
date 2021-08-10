package com.projet.epargne.mapper;

import com.projet.epargne.dto.MoisDto;
import com.projet.epargne.entities.Mois;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MoisMapper {

    MoisMapper INSTANCE = Mappers.getMapper(MoisMapper.class);

    MoisDto entityToDto(Mois mois);

    Mois dtoToEntity(MoisDto moisDto);
}
