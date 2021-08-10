package com.projet.epargne.services.impl;

import com.projet.epargne.dao.CaisseRepository;
import com.projet.epargne.dto.CaisseRequestDTO;
import com.projet.epargne.dto.CaisseResponseDTO;
import com.projet.epargne.entities.Caisse;
import com.projet.epargne.mapper.CaisseMapper;
import com.projet.epargne.services.interfaces.CaisseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CaisseServiceImpl implements CaisseService {
    private final CaisseRepository caisseRepository;
    private final CaisseMapper caisseMapper;

    @Override
    @Transactional
    public CaisseResponseDTO save(CaisseRequestDTO caisseRequestDTO) {
        Caisse caisse = caisseMapper.dtoToEntity(caisseRequestDTO);
        caisse.setIdcaisse(this.nextId());
        Caisse savedCaisse = caisseRepository.save(caisse);
        return caisseMapper.fromCaisseToCaisseResponseDto(savedCaisse);
    }

    private int nextId() {
        Integer next = caisseRepository.nextValue();
        if (next != null && next != 0) {
            return next + 1;
        }
        return 1;
    }
}
