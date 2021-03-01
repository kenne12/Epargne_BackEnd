package com.projet.epargne.services.impl;

import com.projet.epargne.dao.VersementRepository;
import com.projet.epargne.dto.VersementDto;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.mapper.VersementMapper;
import com.projet.epargne.services.interfaces.EpargneService;
import com.projet.epargne.services.interfaces.VersementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class VersementServiceImpl implements VersementService {

    @Autowired
    private VersementRepository versementRepository;

    @Autowired
    private EpargneService epargneService;

    @Override
    public Iterable<VersementDto> getAll() {
        return StreamSupport.stream(versementRepository.findAll().spliterator(), false)
                .map(VersementMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VersementDto findById(Long id) {
        Optional<Versement> versement = versementRepository.findById(id);
        if (versement.isPresent()) {
            return VersementMapper.INSTANCE.entityToDto(versement.get());
        }
        return null;
    }

    @Override
    public VersementDto save(VersementDto dto) {
        Versement versement = VersementMapper.INSTANCE.dtoToEntity(dto);
        if (versement != null) {
            Versement v = epargneService.saveVersement(versement);
            return VersementMapper.INSTANCE.entityToDto(v);
        }
        return null;
    }

    @Override
    public VersementDto edit(VersementDto dto) {
        Versement versement = VersementMapper.INSTANCE.dtoToEntity(dto);
        if (versement != null && versement.getClient() != null) {
            Versement v = epargneService.editVersement(versement);
            return VersementMapper.INSTANCE.entityToDto(v);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        epargneService.deleteVersement(id);
    }

    @Override
    public Long nextValue() {
        return this.next();
    }

    private Long next() {
        Long next = versementRepository.nextValue();
        if (next == null) {
            return 1L;
        }
        return next + 1l;
    }
}
