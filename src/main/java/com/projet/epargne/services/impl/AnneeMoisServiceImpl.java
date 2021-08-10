package com.projet.epargne.services.impl;

import com.projet.epargne.dao.AnneeMoisRepository;
import com.projet.epargne.dto.AnneeMoisDto;
import com.projet.epargne.entities.AnneeMois;
import com.projet.epargne.mapper.AnneeMoisMapper;
import com.projet.epargne.services.interfaces.AnneeMoisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AnneeMoisServiceImpl implements AnneeMoisService {

    private final AnneeMoisRepository anneeMoisRepository;

    @Override
    public Iterable<AnneeMoisDto> getAll() {
        return StreamSupport.stream(anneeMoisRepository.findAllOrder().spliterator(), false)
                .map(AnneeMoisMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnneeMoisDto findById(Long id) {
        Optional<AnneeMois> anneeMois = anneeMoisRepository.findById(id.intValue());
        if (anneeMois.isPresent()) {
            return AnneeMoisMapper.INSTANCE.entityToDto(anneeMois.get());
        }
        return null;
    }

    @Override
    @Transactional
    public AnneeMoisDto save(AnneeMoisDto dto) {
        AnneeMois anneeMois = AnneeMoisMapper.INSTANCE.dtoToEntity(dto);
        anneeMois.setId(this.nextId());
        return AnneeMoisMapper.INSTANCE.entityToDto(anneeMoisRepository.save(anneeMois));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public AnneeMoisDto edit(AnneeMoisDto dto) {
        AnneeMois anneeMois = AnneeMoisMapper.INSTANCE.dtoToEntity(dto);
        if (anneeMois != null && anneeMois.getId() != null) {
            return AnneeMoisMapper.INSTANCE.entityToDto(anneeMoisRepository.save(anneeMois));
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        anneeMoisRepository.deleteById(id.intValue());
    }

    private int nextId() {
        Integer nextId = anneeMoisRepository.nextValue();
        if (nextId == null) {
            return 1;
        } else {
            return nextId + 1;
        }
    }

    @Override
    public List<AnneeMoisDto> findByIdAnne(int idAnnee) {
        return StreamSupport.stream(anneeMoisRepository.findByIdAnnee(idAnnee).spliterator(), false)
                .map(AnneeMoisMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<AnneeMoisDto> findByEtat(boolean etat) {
        return StreamSupport.stream(anneeMoisRepository.findByEtat(etat).spliterator(), false)
                .map(AnneeMoisMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}
