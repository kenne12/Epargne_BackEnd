package com.projet.epargne.rest;

import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.services.interfaces.ClientService;
import com.projet.epargne.services.interfaces.RetraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 *The RetraitRestController
 */

@RestController
@RequestMapping("/api/retrait")
public class RetraitRestController {

    @Autowired
    private RetraitService retraitService;


    @Autowired
    private ClientService clientService;

    /**
     * Gets the all retraits.
     *
     * @return the all retrait
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<RetraitDto> getAllRetrait() {
        return retraitService.getAll();
    }

    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the retrait by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<RetraitDto> getRetraitById(@PathVariable("id") Long id) {
        RetraitDto retraitData = retraitService.findById(id);
        if (retraitData != null) {
            return new ResponseEntity<>(retraitData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates the retrait.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<RetraitDto> createArticle(@RequestBody RetraitDto dto) {
        if (dto.getClient() != null) {
            return new ResponseEntity<>(retraitService.save(dto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update retrait.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<RetraitDto> updateRetrait(@PathVariable(name = "id") Long id, @RequestBody RetraitDto dto) {
        dto.setIdRetrait(id);
        if (dto != null && dto.getClient() != null) {
            return new ResponseEntity<>(retraitService.save(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete retrait.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRetrait(@PathVariable("id") Long id) {
        if (id != null) {
            retraitService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
