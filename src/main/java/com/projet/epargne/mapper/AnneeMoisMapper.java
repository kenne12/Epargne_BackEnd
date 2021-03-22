package com.projet.epargne.mapper;

import com.projet.epargne.dto.AnneeMoisDto;
import com.projet.epargne.entities.AnneeMois;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnneeMoisMapper {

    public AnneeMoisMapper INSTANCE = Mappers.getMapper(AnneeMoisMapper.class);

    AnneeMoisDto entityToDto(AnneeMois anneeMois);

    AnneeMois dtoToEntity(AnneeMoisDto anneeMoisDto);
}
