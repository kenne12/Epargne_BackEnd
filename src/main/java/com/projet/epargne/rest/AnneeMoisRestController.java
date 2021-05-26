package com.projet.epargne.rest;

import com.projet.epargne.dto.AnneeMoisDto;
import com.projet.epargne.dto.MoisDto;
import com.projet.epargne.mapper.MoisMapper;
import com.projet.epargne.services.interfaces.AnneeMoisService;
import com.projet.epargne.services.interfaces.MoisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/anneemois")
public class AnneeMoisRestController {

    @Autowired
    private AnneeMoisService anneeMoisService;

    @Autowired
    private MoisService moisService;

    /**
     * Gets the all anneeMois.
     *
     * @return the all AnneeMois
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<AnneeMoisDto> getAllAnneeMois() {
        return anneeMoisService.getAll();
    }

    /**
     * Gets the client by id.
     *
     * @param id the id
     * @return the annee by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnneeMoisDto> getAnneeById(@PathVariable("id") Long id) {
        AnneeMoisDto anneeMoisDto = anneeMoisService.findById(id);
        if (anneeMoisDto != null) {
            return new ResponseEntity<>(anneeMoisDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates the anneeMois.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<AnneeMoisDto> createAnneeMois(@RequestBody AnneeMoisDto dto) {
        if (dto != null) {
            return new ResponseEntity<>(anneeMoisService.save(dto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update anneeMois.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<AnneeMoisDto> updateAnneeMois(@PathVariable(name = "id") Integer id, @RequestBody AnneeMoisDto dto) {
        if (dto != null && dto.getId() != null) {
            dto.setId(id);
            return new ResponseEntity<>(anneeMoisService.edit(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete anneeMois.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAnneeMois(@PathVariable("id") Long id) {
        if (id != null) {
            anneeMoisService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mois/{id}")
    public @ResponseBody
    Iterable<MoisDto> restMoisByIdAnnee(@PathVariable("id") Integer id) {
        List<AnneeMoisDto> list = anneeMoisService.findByIdAnne(id);
        if (list.isEmpty()) {
            return moisService.getAll();
        } else {
            List<MoisDto> moisAll = (List<MoisDto>) moisService.getAll();

            List<MoisDto> moisGet = new ArrayList<>();

            list.forEach(a -> {
                moisGet.add(MoisMapper.INSTANCE.entityToDto(a.getMois()));
            });

            moisAll.removeAll(moisGet);
            return moisAll;
        }
    }

    /**
     * Gets the all anneeMois.
     *
     * @return the all AnneeMois
     */
    @GetMapping(path = "/all/search/etat")
    public @ResponseBody
    Iterable<AnneeMoisDto> getMoisByEtat(@PathParam("etat") boolean etat) {
        return anneeMoisService.findByEtat(etat);
    }

}
