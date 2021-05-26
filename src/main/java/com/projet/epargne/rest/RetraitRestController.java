package com.projet.epargne.rest;

import com.projet.epargne.dto.RetraitDto;
import com.projet.epargne.dto.RetraitRequest;
import com.projet.epargne.entities.Retrait;
import com.projet.epargne.services.impl.RetraitServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/*
 *The RetraitRestController
 */

@AllArgsConstructor
@RestController
@RequestMapping("/api/retrait")
public class RetraitRestController {

    private final RetraitServiceImpl retraitService;


    /**
     * Gets the all retraits.
     *
     * @return the all retrait
     */
    @GetMapping(path = "/all")
    public ResponseEntity<Page<Retrait>> getAllRetrait(@PathParam(value = "page") int page, @PathParam("size") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(retraitService.getAll(page, size));
    }

    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the retrait by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<RetraitDto> getRetraitById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(retraitService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
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
    public ResponseEntity<RetraitDto> createArticle(@RequestBody RetraitRequest dto) {
        return new ResponseEntity<>(retraitService.save(dto), HttpStatus.CREATED);
        //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update retrait.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<RetraitDto> updateRetrait(@PathVariable(name = "id") Long id, @RequestBody RetraitRequest dto) {
        return new ResponseEntity<>(retraitService.save(dto), HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Delete retrait.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRetrait(@PathVariable("id") Long id) {
        retraitService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
