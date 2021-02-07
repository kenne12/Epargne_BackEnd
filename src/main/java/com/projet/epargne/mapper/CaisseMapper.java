package com.projet.epargne.mapper;

import com.projet.epargne.dto.CaisseDto;
import com.projet.epargne.entities.Caisse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CaisseMapper {
    public CaisseMapper INSTANCE = Mappers.getMapper(CaisseMapper.class);

    public CaisseDto entityToDto(Caisse caisse);

    public Caisse dtoToEntity(CaisseDto caisseDto);
}
