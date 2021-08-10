package com.projet.epargne.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.epargne.dto.UtilisateurRequestDTO;
import com.projet.epargne.dto.UtilisateurResponseDTO;
import com.projet.epargne.entities.Role;
import com.projet.epargne.entities.Utilisateur;
import com.projet.epargne.mapper.UtilisateurMapper;
import com.projet.epargne.services.interfaces.UtilisateurService;
import com.projet.epargne.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@AllArgsConstructor
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurRestController {

    private final UtilisateurService utilisateurService;
    private PasswordEncoder passwordEncoder;

    /**
     * Gets the all utilisateurs.
     *
     * @return the all utilisateurs
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<UtilisateurResponseDTO> getAllUtilisateur() {
        return utilisateurService.getAll();
    }

    /**
     * Gets the utilisateur by id.
     *
     * @param id the id
     * @return the utilisateur by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurById(@PathVariable("id") Long id) {
        UtilisateurResponseDTO utilisateurData = utilisateurService.findById(id);
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
    public ResponseEntity<UtilisateurResponseDTO> createUtilisateur(@RequestBody UtilisateurRequestDTO dto) {
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
    public ResponseEntity<UtilisateurResponseDTO> updateUtilisateur(@PathVariable(name = "id") Integer id, @RequestBody UtilisateurResponseDTO dto) {
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
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") Integer id) {
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
    Iterable<UtilisateurResponseDTO> findByEtat(@PathParam("etat") boolean etat) {
        return utilisateurService.findByEtat(etat);
    }

    /**
     * Update utilisateur.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/changeState/{id}")
    public ResponseEntity<UtilisateurResponseDTO> changeState(@PathVariable(name = "id") Integer id, @RequestBody UtilisateurResponseDTO dto) {
        if (dto != null && dto.getIdUtilisateur() != null) {
            UtilisateurResponseDTO u = utilisateurService.findById(id.longValue());
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
    public ResponseEntity<UtilisateurResponseDTO> resetPassword(@PathVariable(name = "id") Integer id, @RequestBody UtilisateurResponseDTO dto) {
        if (dto != null && dto.getIdUtilisateur() != null) {
            dto.setIdUtilisateur(id);
            dto.setPassword(passwordEncoder.encode("123456"));
            return new ResponseEntity<>(utilisateurService.edit(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    private String decodeToken(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    @GetMapping(path = "/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                String username =this.decodeToken(refresh_token);

                Utilisateur user =  UtilisateurMapper.INSTANCE.fromResponseDtoToEntity(utilisateurService.findByUserName(username));;
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = Utils.putAuthTokens(accessToken , refresh_token);
                response.setContentType("APPLICATION_JSON_VALUE");

                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType("APPLICATION_JSON_VALUE");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
