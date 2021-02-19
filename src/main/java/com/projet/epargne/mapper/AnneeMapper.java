package com.projet.epargne.mapper;

import com.projet.epargne.dto.AnneeDto;
import com.projet.epargne.entities.Annee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnneeMapper {
    public AnneeMapper INSTANCE = Mappers.getMapper(AnneeMapper.class);

    public AnneeDto entityToDto(Annee annee);

    public Annee dtoToEntity(AnneeDto anneeDto);
}
