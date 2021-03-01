package com.projet.epargne.rest;

import com.projet.epargne.dto.ProfessionDto;
import com.projet.epargne.services.interfaces.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profession")
public class ProfessionRestController {

    @Autowired
    private ProfessionService professionService;

    /**
     * Gets the all profession.
     *
     * @return the all profession
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<ProfessionDto> getAllRetrait() {
        return professionService.getAll();
    }
}
