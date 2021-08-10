package com.projet.epargne.services.impl;

import com.projet.epargne.exceptions.ObjectNotFoundException;
import com.projet.epargne.dao.ProfessionRepository;
import com.projet.epargne.dto.ProfessionRequestDTO;
import com.projet.epargne.dto.ProfessionResponseDTO;
import com.projet.epargne.entities.Profession;
import com.projet.epargne.mapper.ProfessionMapper;
import com.projet.epargne.services.interfaces.ProfessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionMapper professionMapper;
    private final ProfessionRepository professionRepository;

    @Override
    public Iterable<ProfessionResponseDTO> getAll() {
        return StreamSupport.stream(professionRepository.findAll().spliterator(), false)
                .map(p -> professionMapper.fromEntityToResponseDto(p))
                .collect(Collectors.toList());
    }

    @Override
    public ProfessionResponseDTO findById(int id) {
        Profession profession = professionRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Profession not found with id : " + id));
        return professionMapper.fromEntityToResponseDto(profession);
    }

    @Override
    public ProfessionResponseDTO save(ProfessionRequestDTO dto) {
        Profession profession = professionMapper.fromRequestDtoToEnity(dto);
        profession.setIdProfession(this.next());
        Profession savedProfession = professionRepository.save(profession);
        return professionMapper.fromEntityToResponseDto(savedProfession);
    }

    @Override
    public ProfessionResponseDTO edit(ProfessionRequestDTO dto) {
        Profession profession = professionMapper.fromRequestDtoToEnity(dto);
        Profession editedProfession = professionRepository.save(profession);
        return professionMapper.fromEntityToResponseDto(editedProfession);
    }

    @Override
    public void deleteById(int id) {
        Profession profession = professionRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Profession not found by id : " + id));
        professionRepository.delete(profession);
    }

    private Integer next() {
        Integer nextValue = professionRepository.nextValue();
        if (nextValue == null || nextValue == 0) {
            return nextValue = 1;
        }
        return nextValue + 1;
    }
}
