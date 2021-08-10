package com.projet.epargne.services.impl;

import com.projet.epargne.dao.AnneeRepository;
import com.projet.epargne.dto.AnneeDto;
import com.projet.epargne.dto.AnneeMoisDto;
import com.projet.epargne.entities.Annee;
import com.projet.epargne.mapper.AnneeMapper;
import com.projet.epargne.services.interfaces.AnneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class AnneeServiceImpl implements AnneeService {

    private final AnneeRepository anneeRepository;

    @Override
    public Iterable<AnneeDto> getAll() {
        return StreamSupport.stream(anneeRepository.findAllOrder().spliterator(), false)
                .map(AnneeMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnneeDto findById(Long id) {
        Optional<Annee> annee = anneeRepository.findById(id.intValue());
        if (annee.isPresent()) {
            return AnneeMapper.INSTANCE.entityToDto(annee.get());
        }
        return null;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public AnneeDto save(AnneeDto dto) {
        Annee annee = AnneeMapper.INSTANCE.dtoToEntity(dto);
        annee.setIdannee(this.nextId());
        return AnneeMapper.INSTANCE.entityToDto(anneeRepository.save(annee));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public AnneeDto edit(AnneeDto dto) {
        Annee annee = AnneeMapper.INSTANCE.dtoToEntity(dto);
        annee.setIdannee(dto.getIdannee());
        return AnneeMapper.INSTANCE.entityToDto(anneeRepository.save(annee));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Annee> annee = anneeRepository.findById(id.intValue());
        if (annee.isPresent()) {
            anneeRepository.deleteById(id.intValue());
        }
    }

    @Override
    public Iterable<AnneeDto> findByEtat(boolean etat) {
        return StreamSupport.stream(anneeRepository.findByEtat(etat).spliterator(), false)
                .map(AnneeMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    private int nextId() {
        Integer nextId = anneeRepository.nextValue();
        if (nextId == null) {
            return 1;
        } else {
            return nextId + 1;
        }
    }
}
