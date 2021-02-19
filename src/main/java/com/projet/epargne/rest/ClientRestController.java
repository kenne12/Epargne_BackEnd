package com.projet.epargne.rest;

import com.projet.epargne.dto.ClientDto;
import com.projet.epargne.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    Iterable<ClientDto> getAllArticle() {
        return clientService.getAll();
    }

    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the client by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getArticleById(@PathVariable("id") Long id) {
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
    public ResponseEntity<ClientDto> createArticle(@RequestBody ClientDto dto) {
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
    @PutMapping("/edit")
    public ResponseEntity<ClientDto> updateArticle(@RequestBody ClientDto dto) {
        if (dto != null && dto.getIdclient() != null) {
            return new ResponseEntity<>(clientService.save(dto), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") Long id) {
        if (id != null) {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
