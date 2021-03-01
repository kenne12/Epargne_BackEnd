package com.projet.epargne.rest;

import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClientService clientService;

    /**
     * Gets the all clients.
     *
     * @return the all clients
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<ClientDto> getAllClient() {
        return clientService.getAll();
    }


    /**
     * Get clients By Nom Or Prenom.
     *
     * @return the all clients
     */
    @GetMapping(path = "/all/search/nomOrPrenom")
    public @ResponseBody
    Iterable<ClientDto> searchClientByNomOrPrenom(@PathParam("keyword") String keyword) {
        return clientService.findByNonOrPrenom("%" + keyword + "%");
    }

    /**
     * Get clients By Nom Or Prenom.
     *
     * @return the all clients
     */
    @GetMapping(path = "/all/search/etat")
    public @ResponseBody
    Iterable<ClientDto> searchClientByEtat(@PathParam("etat") boolean etat) {
        return clientService.findByEtat(etat);
    }


    /**
     * Get clients By Solde Zero.
     *
     * @return the all clients where solde equal 0
     */
    @GetMapping(path = "/all/search/soldeZero")
    public @ResponseBody
    Iterable<ClientDto> searchClientBySoldeZero() {
        return clientService.findBySoldeZero();
    }

    /**
     * Get clients By Solde Gth Zero.
     *
     * @return the all clients where solde ght 0
     */
    @GetMapping(path = "/all/search/soldeGthZero")
    public @ResponseBody
    Iterable<ClientDto> searchClientBySoldeGthZero() {
        return clientService.findBySoldeGthZero();
    }


    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the client by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Long id) {
        ClientDto clientData = clientService.findById(id);
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
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto dto) {
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
    public ResponseEntity<ClientDto> updateClient(@PathVariable(name = "id") Integer id, @RequestBody ClientDto dto) {
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
    @PutMapping("/changeState/{id}")
    public ResponseEntity<ClientDto> changeState(@PathVariable("id") Integer id, @RequestBody ClientDto dto) {
        if (dto != null && dto.getIdclient() != null) {
            return new ResponseEntity<>(clientService.changeState(dto), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Long id) {
        if (id != null) {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
