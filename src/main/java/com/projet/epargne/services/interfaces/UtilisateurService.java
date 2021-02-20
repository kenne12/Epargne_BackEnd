package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.UtilisateurDto;

public interface UtilisateurService extends GenericService<UtilisateurDto> {

    Integer nextValue();
}
