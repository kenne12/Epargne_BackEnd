package com.projet.epargne.services.impl;

import com.projet.epargne.dao.MenuRepository;
import com.projet.epargne.dto.MenuDto;
import com.projet.epargne.entities.Menu;
import com.projet.epargne.mapper.MenuMapper;
import com.projet.epargne.services.interfaces.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Iterable<MenuDto> getAll() {
        return StreamSupport.stream(menuRepository.findAll().spliterator(), false)
                .map(MenuMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDto findById(Long id) {
        Optional<Menu> menu = menuRepository.findById(id.intValue());
        if (menu.isPresent()) {
            return MenuMapper.INSTANCE.entityToDto(menu.get());
        }
        return null;
    }

    @Override
    public MenuDto save(MenuDto dto) {
        Menu m = MenuMapper.INSTANCE.dtoToEntity(dto);
        m.setIdmenu(this.nextId());
        return MenuMapper.INSTANCE.entityToDto(menuRepository.save(m));
    }

    @Override
    public MenuDto edit(MenuDto dto) {
        Menu m = MenuMapper.INSTANCE.dtoToEntity(dto);
        if (m != null && m.getIdmenu() != null) {
            return MenuMapper.INSTANCE.entityToDto(menuRepository.save(m));
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        menuRepository.deleteById(id.intValue());
    }

    private Integer nextId() {
        Integer nextVal = menuRepository.nextValue();
        if (nextVal == null) {
            nextVal = 1;
        } else {
            nextVal += 1;
        }
        return nextVal;
    }
}
