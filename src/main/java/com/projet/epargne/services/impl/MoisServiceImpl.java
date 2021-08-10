package com.projet.epargne.services.impl;

import com.projet.epargne.dao.MoisRepository;
import com.projet.epargne.dto.MoisDto;
import com.projet.epargne.entities.Client;
import com.projet.epargne.entities.Mois;
import com.projet.epargne.mapper.ClientMapper;
import com.projet.epargne.mapper.MoisMapper;
import com.projet.epargne.services.interfaces.MoisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hibernate.cache.spi.support.SimpleTimestamper.next;

@Service
@RequiredArgsConstructor
public class MoisServiceImpl implements MoisService {

    private final MoisRepository moisRepository;

    @Override
    public Iterable<MoisDto> getAll() {
        return StreamSupport.stream(moisRepository.findAllOrder().spliterator(), false)
                .map(MoisMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MoisDto findById(int id) {
        Optional<Mois> mois = moisRepository.findById(id);
        if (mois.isPresent()) {
            return MoisMapper.INSTANCE.entityToDto(mois.get());
        }
        return null;
    }

    @Override
    public MoisDto save(MoisDto dto) {
        Mois mois = MoisMapper.INSTANCE.dtoToEntity(dto);
        if (mois != null) {
            mois.setIdmois(next());
            return MoisMapper.INSTANCE.entityToDto(moisRepository.save(mois));
        }
        return null;
    }

    @Override
    public MoisDto edit(MoisDto dto) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    private int next() {
        Integer next = moisRepository.nextValue();
        if (next == null) {
            return 1;
        }
        return next + 1;
    }
}
