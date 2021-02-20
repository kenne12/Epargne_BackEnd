package com.projet.epargne.rest;

import com.projet.epargne.dto.UtilisateurDto;
import com.projet.epargne.services.interfaces.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {


    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Gets the all utilisateurs.
     *
     * @return the all utilisateurs
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<UtilisateurDto> getAllUtilisateur() {
        return utilisateurService.getAll();
    }

    /**
     * Gets the utilisateur by id.
     *
     * @param id the id
     * @return the utilisateur by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable("id") Long id) {
        UtilisateurDto utilisateurData = utilisateurService.findById(id);
        if (utilisateurData != null) {
            return new ResponseEntity<>(utilisateurData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates the utilisateur.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto dto) {
        if (dto != null) {
            return new ResponseEntity<>(utilisateurService.save(dto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update utilisateur.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/edit")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@RequestBody UtilisateurDto dto) {
        if (dto != null && dto.getIdUtilisateur() != null) {
            return new ResponseEntity<>(utilisateurService.save(dto), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") Long id) {
        if (id != null) {
            utilisateurService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
