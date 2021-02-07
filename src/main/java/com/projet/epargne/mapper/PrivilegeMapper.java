package com.projet.epargne.mapper;

import com.projet.epargne.dto.PrivilegeDto;
import com.projet.epargne.entities.Privilege;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrivilegeMapper {
    public PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);

    public PrivilegeDto entityToDto(Privilege privilege);

    public Privilege dtoToEntity(PrivilegeDto privilegeDto);
}
