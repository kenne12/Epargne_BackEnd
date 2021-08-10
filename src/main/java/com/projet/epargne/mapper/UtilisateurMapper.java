package com.projet.epargne.mapper;

import com.projet.epargne.dto.UtilisateurRequestDTO;
import com.projet.epargne.dto.UtilisateurResponseDTO;
import com.projet.epargne.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    /**
     * The instance.
     */
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    UtilisateurResponseDTO entityToDto(Utilisateur utilisateur);

    Utilisateur dtoToEntity(UtilisateurResponseDTO utilisateurResponseDTO);

    Utilisateur fromRequestDtoToEntity(UtilisateurRequestDTO utilisateurRequestDTO);

    Utilisateur fromResponseDtoToEntity(UtilisateurResponseDTO utilisateurResponseDTO);

}
