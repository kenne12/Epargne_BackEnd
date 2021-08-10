package com.projet.epargne.services.impl;

import com.projet.epargne.exceptions.ObjectNotFoundException;
import com.projet.epargne.dao.MenuRepository;
import com.projet.epargne.dto.MenuRequestDTO;
import com.projet.epargne.entities.Menu;
import com.projet.epargne.mapper.MenuMapper;
import com.projet.epargne.services.interfaces.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public Iterable<MenuRequestDTO> getAll() {
        return StreamSupport.stream(menuRepository.findAll().spliterator(), false)
                .map(MenuMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuRequestDTO findById(int idClient) {
        Menu menu = menuRepository.findById(idClient).orElseThrow(() -> new ObjectNotFoundException("Menu not found with id : " + idClient));
        return MenuMapper.INSTANCE.entityToDto(menu);
    }

    @Override
    public MenuRequestDTO save(MenuRequestDTO dto) {
        Menu m = MenuMapper.INSTANCE.dtoToEntity(dto);
        m.setIdmenu(this.nextId());
        return MenuMapper.INSTANCE.entityToDto(menuRepository.save(m));
    }

    @Override
    public MenuRequestDTO edit(MenuRequestDTO dto) {
        Menu m = MenuMapper.INSTANCE.dtoToEntity(dto);
        if (m != null && m.getIdmenu() != null) {
            return MenuMapper.INSTANCE.entityToDto(menuRepository.save(m));
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        menuRepository.deleteById(id);
    }

    private Integer nextId() {
        Integer nextVal = menuRepository.nextValue();
        if (nextVal == null || nextVal == 0) {
            return 1;
        } else {
            return (nextVal + 1);
        }
    }
}
