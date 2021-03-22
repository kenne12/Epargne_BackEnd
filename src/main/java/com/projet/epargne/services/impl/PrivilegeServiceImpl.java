package com.projet.epargne.services.impl;

import com.projet.epargne.dao.PrivilegeRepository;
import com.projet.epargne.dto.PrivilegeDto;
import com.projet.epargne.entities.Privilege;
import com.projet.epargne.mapper.PrivilegeMapper;
import com.projet.epargne.services.interfaces.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public Iterable<PrivilegeDto> getAll() {
        return StreamSupport.stream(privilegeRepository.findAll().spliterator(), false)
                .map(PrivilegeMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrivilegeDto findById(Long id) {
        Optional<Privilege> privilege = privilegeRepository.findById(id);
        if (privilege.isPresent()) {
            return PrivilegeMapper.INSTANCE.entityToDto(privilege.get());
        }
        return null;
    }

    @Override
    public PrivilegeDto save(PrivilegeDto dto) {
        Privilege p = PrivilegeMapper.INSTANCE.dtoToEntity(dto);
        p.setIdPrivilege(nextId());
        return PrivilegeMapper.INSTANCE.entityToDto(privilegeRepository.save(p));
    }

    @Override
    public PrivilegeDto edit(PrivilegeDto dto) {
        Privilege p = PrivilegeMapper.INSTANCE.dtoToEntity(dto);
        if (p != null && p.getIdPrivilege() != null) {
            return PrivilegeMapper.INSTANCE.entityToDto(privilegeRepository.save(p));
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        privilegeRepository.deleteById(id);
    }

    @Override
    public Iterable<PrivilegeDto> findByIdUtilisateur(int idUtilisateur) {
        return StreamSupport.stream(privilegeRepository.findPrivilegeByIdUtilisateur(idUtilisateur).spliterator(), false)
                .map(PrivilegeMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    public Long nextValue() {
        return nextId();
    }

    private Long nextId() {
        Long nextId = privilegeRepository.nextValue();
        if (nextId == null) {
            nextId = 1l;
        } else {
            nextId += 1;
        }
        return nextId;
    }
}
