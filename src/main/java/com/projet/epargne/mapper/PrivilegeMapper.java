package com.projet.epargne.mapper;

import com.projet.epargne.dto.PrivilegeDto;
import com.projet.epargne.entities.Privilege;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);

    PrivilegeDto entityToDto(Privilege privilege);

    Privilege dtoToEntity(PrivilegeDto privilegeDto);
}
