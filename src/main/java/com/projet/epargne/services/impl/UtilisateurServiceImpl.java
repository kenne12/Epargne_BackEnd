package com.projet.epargne.services.impl;

import com.projet.epargne.dao.UtilisateurRepository;
import com.projet.epargne.dto.UtilisateurDto;
import com.projet.epargne.entities.Utilisateur;
import com.projet.epargne.mapper.UtilisateurMapper;
import com.projet.epargne.services.interfaces.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
            utilisateur.setPassword(bCryptPasswordEncoder().encode(utilisateur.getPassword()));
            //utilisateur.setPassword(new ShaHash().hash(utilisateur.getPassword()));
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
    public Iterable<UtilisateurDto> findByEtat(boolean etat) {
        return StreamSupport.stream(utilisateurRepository.findAllByActif(etat).spliterator(), false)
                .map(UtilisateurMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
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

    @Override
    public UtilisateurDto findByUserName(String userName) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByUserName(userName);
        if (utilisateur.isPresent()) {
            return UtilisateurMapper.INSTANCE.entityToDto(utilisateur.get());
        }
        return null;

    }
}
