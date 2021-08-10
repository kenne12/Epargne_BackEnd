package com.projet.epargne.rest;

import com.projet.epargne.dto.ProfessionRequestDTO;
import com.projet.epargne.dto.ProfessionResponseDTO;
import com.projet.epargne.services.interfaces.ProfessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profession")
public class ProfessionRestController {

    private final ProfessionService professionService;

    public ProfessionRestController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    /**
     * Gets the all profession.
     *
     * @return the all profession
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<ProfessionResponseDTO> getAll() {
        return professionService.getAll();
    }
}
