package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.UtilisateurDto;

public interface UtilisateurService extends GenericService<UtilisateurDto> {

    Integer nextValue();

    Iterable<UtilisateurDto> findByEtat(boolean etat);

    UtilisateurDto findByUserName(String userName);
}
