package com.projet.epargne.rest;

import com.projet.epargne.dto.MoisDto;
import com.projet.epargne.services.interfaces.MoisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mois")
public class MoisRestController {

    @Autowired
    private MoisService moisService;

    /**
     * Gets the all mois.
     *
     * @return the all mois
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<MoisDto> getAll() {
        return moisService.getAll();
    }

    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the mois by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<MoisDto> getById(@PathVariable("id") Long id) {
        MoisDto moisDto = moisService.findById(id);
        if (moisDto != null) {
            return new ResponseEntity<>(moisDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
