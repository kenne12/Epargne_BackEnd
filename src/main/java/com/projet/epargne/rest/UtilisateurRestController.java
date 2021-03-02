package com.projet.epargne.rest;

import com.projet.epargne.dto.UtilisateurDto;
import com.projet.epargne.services.interfaces.UtilisateurService;
import com.projet.epargne.utils.ShaHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
    @PutMapping("/edit/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable(name = "id") Integer id, @RequestBody UtilisateurDto dto) {
        if (dto != null && dto.getIdUtilisateur() != null) {
            return new ResponseEntity<>(utilisateurService.edit(dto), HttpStatus.OK);
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

    /**
     * Gets the all utilisateurs by etat.
     *
     * @return the all utilisateurs
     */
    @GetMapping(path = "/all/search/etat")
    public @ResponseBody
    Iterable<UtilisateurDto> findByEtat(@PathParam("etat") boolean etat) {
        return utilisateurService.findByEtat(etat);
    }

    /**
     * Update utilisateur.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/changeState/{id}")
    public ResponseEntity<UtilisateurDto> changeState(@PathVariable(name = "id") Integer id, @RequestBody UtilisateurDto dto) {
        if (dto != null && dto.getIdUtilisateur() != null) {
            UtilisateurDto u = utilisateurService.findById(id.longValue());
            dto.setPassword(u.getPassword());
            return new ResponseEntity<>(utilisateurService.edit(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * reset user password.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/resetPassword/{id}")
    public ResponseEntity<UtilisateurDto> resetPassword(@PathVariable(name = "id") Integer id, @RequestBody UtilisateurDto dto) {
        if (dto != null && dto.getIdUtilisateur() != null) {
            dto.setIdUtilisateur(id);
            dto.setPassword(new ShaHash().hash("123456"));
            return new ResponseEntity<>(utilisateurService.edit(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
