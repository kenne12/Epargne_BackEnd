package com.projet.epargne.services.impl;

import com.projet.epargne.exceptions.ObjectNotFoundException;
import com.projet.epargne.dao.ClientRepository;
import com.projet.epargne.dto.ClientRequestDTO;
import com.projet.epargne.dto.ClientResponseDTO;
import com.projet.epargne.entities.Client;
import com.projet.epargne.mapper.ClientMapper;
import com.projet.epargne.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Iterable<ClientResponseDTO> getAll() {
        return StreamSupport.stream(clientRepository.findAllOrOrderByNumeroCarnet().spliterator(), false)
                .map(ClientMapper.INSTANCE::fromClientToClientResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ClientResponseDTO> findByNonOrPrenom(String keyword) {
        return StreamSupport.stream(clientRepository.findByNomLikeOrPrenomLike(keyword).spliterator(), false)
                .map(ClientMapper.INSTANCE::fromClientToClientResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ClientResponseDTO> findByEtat(boolean etat) {
        return StreamSupport.stream(clientRepository.findByEtat(etat).spliterator(), false)
                .map(ClientMapper.INSTANCE::fromClientToClientResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ClientResponseDTO> findBySoldeZero() {
        return StreamSupport.stream(clientRepository.findBySoldeEqualsZero().spliterator(), false)
                .map(ClientMapper.INSTANCE::fromClientToClientResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ClientResponseDTO> findBySoldeGthZero() {
        return StreamSupport.stream(clientRepository.findBySoldeGthZero().spliterator(), false)
                .map(ClientMapper.INSTANCE::fromClientToClientResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponseDTO findById(int id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Client not found with id : " + id));
        return ClientMapper.INSTANCE.fromClientToClientResponseDTO(client);
    }

    @Override
    @Transactional
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) {
        Client client = ClientMapper.INSTANCE.dtoToEntity(clientRequestDTO);
        if (client != null) {
            client.setIdclient(next());
            return ClientMapper.INSTANCE.fromClientToClientResponseDTO(clientRepository.save(client));
        }
        return null;
    }

    @Override
    @Transactional
    public ClientResponseDTO edit(ClientResponseDTO clientResponseDTO) {
        Client client = ClientMapper.INSTANCE.fromClientResponseDtoToClient(clientResponseDTO);
        if (client != null && client.getIdclient() != null) {
            return ClientMapper.INSTANCE.fromClientToClientResponseDTO(clientRepository.save(client));
        }
        return null;
    }

    @Override
    public ClientResponseDTO changeState(int idClient, boolean state) {
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new ObjectNotFoundException("Client not found by Id : " + idClient));
        client.setEtat(state);
        return clientMapper.fromClientToClientResponseDTO(client);
    }

    @Override
    public void deleteById(int id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Client not found with Id : " + id));
        clientRepository.deleteById(id);
    }


    private Integer next() {
        Integer nextValue = clientRepository.nexId();
        if (nextValue == null || nextValue == 0) {
            return nextValue = 1;
        }
        return nextValue + 1;
    }
}
