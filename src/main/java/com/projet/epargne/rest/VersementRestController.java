package com.projet.epargne.rest;

import com.projet.epargne.dto.VersementDto;
import com.projet.epargne.dto.VersementRequest;
import com.projet.epargne.entities.Versement;
import com.projet.epargne.services.impl.VersementServiceImpl;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/*
 *The VersementRestController
 */
@RestController
@RequestMapping("/api/versement")
@AllArgsConstructor
public class VersementRestController {

    private final VersementServiceImpl versementService;

    /**
     * Gets the all versements.
     *
     * @return the all versement
     */
    @GetMapping(path = "/all")
    public ResponseEntity<Page<Versement>> getAllVersement(@PathParam(value = "page") int page, @PathParam("size") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(versementService.getAll(page, size));
    }

    /**
     * Gets the versment by id.
     *
     * @param id the id
     * @return the versement by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VersementDto> getVersementById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(versementService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
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
    public ResponseEntity<VersementDto> createVersement(@RequestBody VersementRequest versementRequest) {
        try {
            return new ResponseEntity<>(versementService.save(versementRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update versement.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<VersementDto> updateVersement(@PathVariable(name = "id") Long id, @RequestBody VersementRequest versementRequest) {
        if (versementRequest != null && versementRequest.getIdclient() != 0) {
            versementRequest.setIdVersement(id);
            return new ResponseEntity<>(versementService.edit(versementRequest), HttpStatus.OK);
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


    /**
     * Gets the versement by idClient.
     *
     * @return the all versement
     * @Param idClient , dateDebut , dateFin
     */
    @GetMapping(path = "/search/client")
    public @ResponseBody
    Iterable<VersementDto> getByIdClientIntervalDate(@PathParam("idClient") int idClient, @PathParam("dateDebut") String dateDebut, @PathParam("dateFin") String dateFin) {
        return versementService.findByIdClientIntervalDate(idClient, new DateTime(dateDebut).toDate(), new DateTime(dateFin).toDate());
    }


    /**
     * Gets the versement by idClient.
     *
     * @return the all versement
     * @Param idClient , dateDebut , dateFin
     */
    @GetMapping(path = "/search/date")
    public @ResponseBody
    Iterable<VersementDto> getByIdIntervalDate(@PathParam("dateDebut") String dateDebut, @PathParam("dateFin") String dateFin) {
        return versementService.findByIntervalDate(new DateTime(dateDebut).toDate(), new DateTime(dateFin).toDate());
    }


}
