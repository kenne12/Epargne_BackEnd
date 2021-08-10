package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.PrivilegeDto;

public interface PrivilegeService extends GenericService<PrivilegeDto , PrivilegeDto> {

    Iterable<PrivilegeDto> findByIdUtilisateur(int idUtilisateur);
}
