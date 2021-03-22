package com.projet.epargne.mapper;

import com.projet.epargne.dto.MoisDto;
import com.projet.epargne.entities.Mois;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MoisMapper {

    public MoisMapper INSTANCE = Mappers.getMapper(MoisMapper.class);

    public MoisDto entityToDto(Mois mois);

    public Mois dtoToEntity(MoisDto moisDto);
}
