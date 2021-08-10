package com.projet.epargne.services.impl;

import com.projet.epargne.dto.VersementResponseDTO;
import com.projet.epargne.exceptions.ObjectNotFoundException;
import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dao.VersementRepository;
import com.projet.epargne.dto.VersementRequestDTO;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.mapper.VersementMapper;
import com.projet.epargne.services.interfaces.EpargneService;
import com.projet.epargne.services.interfaces.VersementService;
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
public class VersementServiceImpl implements VersementService {

    private final VersementRepository versementRepository;
    private final EpargneService epargneService;

    @Override
    public Page<Versement> getAll(int page, int size) {
        return versementRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public VersementResponseDTO findById(Long id) {
        Versement versement = versementRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Versement not found with id" + id));
        return VersementMapper.INSTANCE.entityToDto(versement);
    }

    @Override
    public VersementResponseDTO save(VersementRequestDTO versementRequestDTO) {
        Versement v = epargneService.saveVersement(versementRequestDTO);
        return VersementMapper.INSTANCE.entityToDto(v);
    }

    @Override
    public VersementResponseDTO edit(VersementRequestDTO versementRequestDTO) {
        Versement v = epargneService.editVersement(versementRequestDTO);
        return VersementMapper.INSTANCE.entityToDto(v);
    }

    public void deleteById(Long id) {
        epargneService.deleteVersement(id);
    }

    @Override
    public Iterable<VersementResponseDTO> findByIdClientBetwenToDates(int idClient, Date dateDebut, Date dateFin) {
        return StreamSupport.stream(versementRepository.findByIdClientIntervalDate(idClient, dateDebut, dateFin).spliterator(), false)
                .map(VersementMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<VersementResponseDTO> findByIntervalDate(Date dateDebut, Date dateFin) {
        return StreamSupport.stream(versementRepository.findByIntervalDate(dateDebut, dateFin).spliterator(), false)
                .map(VersementMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

}
