package com.projet.epargne.services.impl;

import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.entities.Client;
import com.projet.epargne.mapper.ClientMapper;
import com.projet.epargne.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    public ClientRepository clientRepository;

    @Override
    public Iterable<ClientDto> getAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(ClientMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ClientMapper.INSTANCE.entityToDto(client.get());
        }
        return null;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        Client client = ClientMapper.INSTANCE.dtoToEntity(dto);
        if (client != null) {
            return ClientMapper.INSTANCE.entityToDto(clientRepository.save(client));
        }
        return null;
    }

    @Override
    public ClientDto edit(ClientDto dto) {
        Client client = ClientMapper.INSTANCE.dtoToEntity(dto);
        if (client != null && client.getIdclient() != null) {
            return ClientMapper.INSTANCE.entityToDto(clientRepository.save(client));
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
