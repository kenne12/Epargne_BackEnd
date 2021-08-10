package com.projet.epargne.services.impl;

import com.projet.epargne.exceptions.ObjectNotFoundException;
import com.projet.epargne.dao.UtilisateurRepository;
import com.projet.epargne.dto.UtilisateurResponseDTO;
import com.projet.epargne.dto.UtilisateurRequestDTO;
import com.projet.epargne.entities.Utilisateur;
import com.projet.epargne.mapper.UtilisateurMapper;
import com.projet.epargne.services.interfaces.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService , UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;
    private UtilisateurMapper utilisateurMapper;

    @Override
    public Iterable<UtilisateurResponseDTO> getAll() {
        return StreamSupport.stream(utilisateurRepository.findAll().spliterator(), false)
                .map(UtilisateurMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UtilisateurResponseDTO> getAll(int page, int size) {
        Page<UtilisateurResponseDTO> pageable;
        utilisateurRepository.findAll(PageRequest.of(page, size)).stream().map(UtilisateurMapper.INSTANCE::entityToDto).collect(Collectors.toList());
        return null;
    }

    @Override
    public UtilisateurResponseDTO findById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id.intValue()).orElseThrow(() -> new ObjectNotFoundException("User not found with id : " + id));
        return UtilisateurMapper.INSTANCE.entityToDto(utilisateur);
    }


    @Override
    public UtilisateurResponseDTO save(UtilisateurRequestDTO utilisateurRequest) {

        if (!utilisateurRequest.getPassword().equals(utilisateurRequest.getRepeatPassword())) {
            throw new ObjectNotFoundException("Veuillez saisir deux mots de passe identiques");
        }

        Utilisateur utilisateur = utilisateurMapper.fromRequestDtoToEntity(utilisateurRequest);
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setDateCreation(Instant.now());
        utilisateur.setIdUtilisateur(this.next());
        return UtilisateurMapper.INSTANCE.entityToDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UtilisateurResponseDTO edit(UtilisateurResponseDTO dto) {
        Utilisateur utilisateur = UtilisateurMapper.INSTANCE.dtoToEntity(dto);
        if (utilisateur != null) {
            return UtilisateurMapper.INSTANCE.entityToDto(utilisateurRepository.save(utilisateur));
        }
        return null;
    }

    @Override
    public Iterable<UtilisateurResponseDTO> findByEtat(boolean etat) {
        return StreamSupport.stream(utilisateurRepository.findAllByActif(etat).spliterator(), false)
                .map(UtilisateurMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(int id) {
        utilisateurRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found with id : " + id));
        utilisateurRepository.deleteById(id);
    }

    private Integer next() {
        try {
            Integer nextValue = utilisateurRepository.nextValue();
            if (nextValue != null) {
                return (nextValue + 1);
            }
            return 1;
        } catch (Exception e) {
            return 1;
        }
    }

    public UtilisateurResponseDTO findByUserName(String userName) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(userName);
        if (utilisateur!=null) {
            return UtilisateurMapper.INSTANCE.entityToDto(utilisateur);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByUsername(username);
        if (user == null) {
            log.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        } else {
            log.info("User found in the database {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
