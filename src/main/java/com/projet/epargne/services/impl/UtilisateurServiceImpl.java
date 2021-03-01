package com.projet.epargne.services.impl;

import com.projet.epargne.dao.UtilisateurRepository;
import com.projet.epargne.dto.UtilisateurDto;
import com.projet.epargne.entities.Client;
import com.projet.epargne.entities.Utilisateur;
import com.projet.epargne.mapper.ClientMapper;
import com.projet.epargne.mapper.UtilisateurMapper;
import com.projet.epargne.services.interfaces.UtilisateurService;
import com.projet.epargne.utils.ShaHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Iterable<UtilisateurDto> getAll() {
        return StreamSupport.stream(utilisateurRepository.findAll().spliterator(), false)
                .map(UtilisateurMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto findById(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id.intValue());
        if (utilisateur.isPresent()) {
            return UtilisateurMapper.INSTANCE.entityToDto(utilisateur.get());
        }
        return null;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        Utilisateur utilisateur = UtilisateurMapper.INSTANCE.dtoToEntity(dto);
        if (utilisateur != null) {
            utilisateur.setIdUtilisateur(this.next());
            utilisateur.setPassword(new ShaHash().hash(utilisateur.getPassword()));
            return UtilisateurMapper.INSTANCE.entityToDto(utilisateurRepository.save(utilisateur));
        }
        return null;
    }

    @Override
    public UtilisateurDto edit(UtilisateurDto dto) {
        Utilisateur utilisateur = UtilisateurMapper.INSTANCE.dtoToEntity(dto);
        if (utilisateur != null) {
            return UtilisateurMapper.INSTANCE.entityToDto(utilisateurRepository.save(utilisateur));
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id.intValue());
    }

    @Override
    public Integer nextValue() {
        return this.next();
    }

    private Integer next() {
        Integer nextValue = utilisateurRepository.nextValue();
        if (nextValue == null || nextValue == 0) {
            return nextValue = 1;
        }
        return nextValue + 1;
    }
}
