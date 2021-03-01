package com.projet.epargne.rest;

import com.projet.epargne.dto.VersementDto;
import com.projet.epargne.services.interfaces.EpargneService;
import com.projet.epargne.services.interfaces.VersementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 *The VersementRestController
 */
@RestController
@RequestMapping("/api/versement")
public class VersementRestController {

    @Autowired
    private VersementService versementService;
    

    /**
     * Gets the all versements.
     *
     * @return the all versement
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<VersementDto> getAllVersement() {
        return versementService.getAll();
    }


    /**
     * Gets the versment by id.
     *
     * @param id the id
     * @return the versement by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VersementDto> getVersementById(@PathVariable("id") Long id) {
        VersementDto versementData = versementService.findById(id);
        if (versementData != null) {
            return new ResponseEntity<>(versementData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates the versement.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<VersementDto> createVersement(@RequestBody VersementDto dto) {
        if (dto.getClient() != null) {
            return new ResponseEntity<>(versementService.save(dto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update versement.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<VersementDto> updateVersement(@PathVariable(name = "id") Long id, @RequestBody VersementDto dto) {
        if (dto != null && dto.getClient() != null) {
            return new ResponseEntity<>(versementService.edit(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete versement.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteVersement(@PathVariable("id") Long id) {
        if (id != null) {
            versementService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
