package com.projet.epargne.mapper;

import com.projet.epargne.dto.MouchardDto;
import com.projet.epargne.entities.Mouchard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MouchardMapper {
    public MouchardMapper INSTANCE = Mappers.getMapper(MouchardMapper.class);

    public MouchardDto entityToDto(Mouchard mouchard);

    public Mouchard dtoToEntity(MouchardDto mouchardDto);
}
