package com.projet.epargne.rest;

import com.projet.epargne.dto.AnneeDto;
import com.projet.epargne.services.interfaces.AnneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/annee")
public class AnneeRestController {

    @Autowired
    private AnneeService anneeService;

    /**
     * Gets the all annee.
     *
     * @return the all annee
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<AnneeDto> getAllAnnee() {
        return anneeService.getAll();
    }

    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the annee by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnneeDto> getAnneeById(@PathVariable("id") Long id) {
        AnneeDto anneeDto = anneeService.findById(id);
        if (anneeDto != null) {
            return new ResponseEntity<>(anneeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates the annee.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<AnneeDto> createAnnee(@RequestBody AnneeDto dto) {
        if (dto != null) {
            return new ResponseEntity<>(anneeService.save(dto), HttpStatus.CREATED);
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
    public ResponseEntity<AnneeDto> updateAnnee(@PathVariable(name = "id") Integer id, @RequestBody AnneeDto dto) {
        if (dto != null && dto.getIdannee() != null) {
            dto.setIdannee(id);
            return new ResponseEntity<>(anneeService.edit(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete annee.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAnnee(@PathVariable("id") Long id) {
        if (id != null) {
            anneeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Gets the annee by id.
     *
     * @param etat the etta
     * @return the annee by etat
     */
    @GetMapping("/all/search")
    public Iterable<AnneeDto> getAnneeByEtat(@PathParam("etat") boolean etat) {
        return anneeService.findByEtat(etat);
    }
}
