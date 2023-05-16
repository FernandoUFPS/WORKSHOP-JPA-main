package com.remolina.workshop.controller;

import com.remolina.workshop.dto.CitaDTO;
import com.remolina.workshop.exception.BadRequestException;
import com.remolina.workshop.exception.NotFoundException;
import com.remolina.workshop.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/agendar")
    public ResponseEntity<?> agendarCita(@RequestBody CitaDTO citaDTO) {
        try {
            CitaDTO citaCreada = citaService.agendarCita(citaDTO);
            return new ResponseEntity<>(citaCreada, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/agendar/{id}")
    public ResponseEntity<?> obtenerCita(@PathVariable("id") Long idCita) {
        try {
            CitaDTO citaObtenida = citaService.obtenerCita(idCita);
            return new ResponseEntity<>(citaObtenida, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
