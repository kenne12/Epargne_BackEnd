package com.projet.epargne.services.impl;

import com.projet.epargne.dao.RetraitRepository;
import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.mapper.RetraitMapper;
import com.projet.epargne.services.interfaces.RetraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class RetraitServiceImpl implements RetraitService {

    @Autowired
    private RetraitRepository retraitRepository;

    @Override
    public Iterable<RetraitDto> getAll() {
        return StreamSupport.stream(retraitRepository.findAll().spliterator(), false)
                .map(RetraitMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetraitDto findById(Long id) {
        Optional<Retrait> retrait = retraitRepository.findById(id);
        if (retrait.isPresent()) {
            return RetraitMapper.INSTANCE.entityToDto(retrait.get());
        }
        return null;
    }

    @Override
    public RetraitDto save(RetraitDto dto) {
        Retrait retrait = RetraitMapper.INSTANCE.dtoToEntity(dto);
        if (retrait != null) {
            return RetraitMapper.INSTANCE.entityToDto(retraitRepository.save(retrait));
        }
        return null;
    }

    @Override
    public RetraitDto edit(RetraitDto dto) {
        Retrait retrait = RetraitMapper.INSTANCE.dtoToEntity(dto);
        if (retrait != null && retrait.getClient() != null) {
            return RetraitMapper.INSTANCE.entityToDto(retraitRepository.save(retrait));
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        retraitRepository.deleteById(id);
    }

    @Override
    public Long nextValue() {
        return this.next();
    }


    private Long next() {
        Long next = retraitRepository.nextValue();
        if (next == null) {
            return 1L;
        }
        return next + 1l;
    }
}
