package com.projet.epargne.dao;

import com.projet.epargne.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
