package com.projet.epargne.mapper;

import com.projet.epargne.dto.UtilisateurDto;
import com.projet.epargne.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UtilisateurMapper {
    /**
     * The instance.
     */
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    /**
     * Entity to dto.
     *
     * @param Utilisateur the AchatDocumentEntete
     * @return the UtilisateurDto dto
     */
    UtilisateurDto entityToDto(Utilisateur utilisateur);

    /**
     * Dto to entity.
     *
     * @param dto the dto
     * @return the Utilisateur
     */
    Utilisateur dtoToEntity(UtilisateurDto dto);

}
