package com.projet.epargne.services.impl;

import com.projet.epargne.dao.ProfessionRepository;
import com.projet.epargne.dto.ProfessionDto;
import com.projet.epargne.mapper.ClientMapper;
import com.projet.epargne.mapper.ProfessionMapper;
import com.projet.epargne.services.interfaces.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    @Override
    public Iterable<ProfessionDto> getAll() {
        return StreamSupport.stream(professionRepository.findAll().spliterator(), false)
                .map(ProfessionMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessionDto findById(Long id) {
        return null;
    }

    @Override
    public ProfessionDto save(ProfessionDto dto) {
        return null;
    }

    @Override
    public ProfessionDto edit(ProfessionDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
