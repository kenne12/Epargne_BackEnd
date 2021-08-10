package com.projet.epargne.rest;

import com.projet.epargne.dto.ClientRequestDTO;
import com.projet.epargne.dto.ClientResponseDTO;
import com.projet.epargne.services.interfaces.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * The Class ClientRestController.
 */
@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Gets the all clients.
     *
     * @return the all clients
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<ClientResponseDTO> getAllClient() {
        return clientService.getAll();
    }


    /**
     * Get clients By Nom Or Prenom.
     *
     * @return the all clients
     */
    @GetMapping(path = "/all/search/nomOrPrenom")
    public @ResponseBody
    Iterable<ClientResponseDTO> searchClientByNomOrPrenom(@PathParam("keyword") String keyword) {
        return clientService.findByNonOrPrenom("%" + keyword + "%");
    }

    /**
     * Get clients By Nom Or Prenom.
     *
     * @return the all clients
     */
    @GetMapping(path = "/all/search/etat")
    public @ResponseBody
    Iterable<ClientResponseDTO> searchClientByEtat(@PathParam("etat") boolean etat) {
        return clientService.findByEtat(etat);
    }


    /**
     * Get clients By Solde Zero.
     *
     * @return the all clients where solde equal 0
     */
    @GetMapping(path = "/all/search/soldeZero")
    public @ResponseBody
    Iterable<ClientResponseDTO> searchClientBySoldeZero() {
        return clientService.findBySoldeZero();
    }

    /**
     * Get clients By Solde Gth Zero.
     *
     * @return the all clients where solde ght 0
     */
    @GetMapping(path = "/all/search/soldeGthZero")
    public @ResponseBody
    Iterable<ClientResponseDTO> searchClientBySoldeGthZero() {
        return clientService.findBySoldeGthZero();
    }


    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the client by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable("id") Integer id) {
        ClientResponseDTO clientData = clientService.findById(id);
        if (clientData != null) {
            return new ResponseEntity<>(clientData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates the client.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO dto) {
        if (dto.getIdclient() != null) {
            return new ResponseEntity<>(clientService.save(dto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update client.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable(name = "id") Integer id, @RequestBody ClientRequestDTO dto) {
        if (dto != null && dto.getIdclient() != null) {
            return new ResponseEntity<>(clientService.save(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Change State Client.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/changeState/{id}/")
    public ResponseEntity<ClientResponseDTO> changeState(@PathVariable("id") Integer id, @PathParam(value = "etat")  boolean etat   ) {
        if ( id != null ) {
            return new ResponseEntity<>(clientService.changeState(id , etat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete article.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Integer id) {
        if (id != null) {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
