package com.projet.epargne.services.impl;

import com.projet.epargne.ObjectNotFoundException;
import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dao.VersementRepository;
import com.projet.epargne.dto.VersementDto;
import com.projet.epargne.dto.VersementRequest;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.mapper.VersementMapper;
import com.projet.epargne.services.interfaces.EpargneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@AllArgsConstructor
public class VersementServiceImpl {

    private final VersementRepository versementRepository;
    private final EpargneService epargneService;
    private final ClientRepository clientRepository;

    public Page<Versement> getAll(int page, int size) {
        return versementRepository.findAll(PageRequest.of(page, size));
    }

    public VersementDto findById(Long id) {
        Versement versement = versementRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Versement not found with id" + id));
        return VersementMapper.INSTANCE.entityToDto(versement);
    }

    public VersementDto save(VersementRequest versementRequest) {
        Versement v = epargneService.saveVersement(versementRequest);
        return VersementMapper.INSTANCE.entityToDto(v);
    }

    public VersementDto edit(VersementRequest versementRequest) {
        Versement v = epargneService.editVersement(versementRequest);
        return VersementMapper.INSTANCE.entityToDto(v);
    }

    public void deleteById(Long id) {
        epargneService.deleteVersement(id);
    }


    public Iterable<VersementDto> findByIdClientIntervalDate(int idClient, Date dateDebut, Date dateFin) {
        return StreamSupport.stream(versementRepository.findByIdClientIntervalDate(idClient, dateDebut, dateFin).spliterator(), false)
                .map(VersementMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }


    public Iterable<VersementDto> findByIntervalDate(Date dateDebut, Date dateFin) {
        return StreamSupport.stream(versementRepository.findByIntervalDate(dateDebut, dateFin).spliterator(), false)
                .map(VersementMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }


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
