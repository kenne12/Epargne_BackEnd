package com.projet.epargne.mapper;

import com.projet.epargne.dto.MenuRequestDTO;
import com.projet.epargne.entities.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuMapper {

    public MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    public MenuRequestDTO entityToDto(Menu menu);

    public Menu dtoToEntity(MenuRequestDTO menuRequestDTO);
}
