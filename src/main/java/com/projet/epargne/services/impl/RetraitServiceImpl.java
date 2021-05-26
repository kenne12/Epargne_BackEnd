package com.projet.epargne.services.impl;

import com.projet.epargne.ObjectNotFoundException;
import com.projet.epargne.dao.RetraitRepository;
import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.dto.RetraitRequest;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.mapper.RetraitMapper;
import com.projet.epargne.services.interfaces.EpargneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RetraitServiceImpl {

    private final RetraitRepository retraitRepository;
    private final EpargneService epargneService;

    public Page<Retrait> getAll(int page, int size) {
        return retraitRepository.findAll(PageRequest.of(page, size));
    }

    public RetraitDto findById(Long id) {
        Retrait retrait = retraitRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Retrait not found with id : " + id));
        return RetraitMapper.INSTANCE.entityToDto(retrait);
    }

    public RetraitDto save(RetraitRequest retraitRequest) {
        Retrait r = epargneService.saveRetrait(retraitRequest);
        return RetraitMapper.INSTANCE.entityToDto(r);
    }

    public RetraitDto edit(RetraitRequest retraitRequest) {
        Retrait r = epargneService.editRetrait(retraitRequest);
        return RetraitMapper.INSTANCE.entityToDto(r);
    }

    public void deleteById(Long id) {
        epargneService.deleteRetrait(id);
    }

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
