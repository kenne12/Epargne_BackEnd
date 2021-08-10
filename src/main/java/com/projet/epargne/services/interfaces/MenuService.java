package com.projet.epargne.services.interfaces;

import com.projet.epargne.dto.MenuRequestDTO;

public interface MenuService {

    Iterable<MenuRequestDTO> getAll();

    MenuRequestDTO findById(int id);

    MenuRequestDTO save(MenuRequestDTO dto);

    MenuRequestDTO edit(MenuRequestDTO dto);

    void deleteById(int id);
}
