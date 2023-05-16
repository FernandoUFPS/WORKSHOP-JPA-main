package com.remolina.workshop.service;

import com.remolina.workshop.dto.CitaDTO;
import com.remolina.workshop.exception.BadRequestException;
import com.remolina.workshop.exception.NotFoundException;
import com.remolina.workshop.model.Cita;
import com.remolina.workshop.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.Optional;

@Service
public class CitaMService {
    private CitaRepository citaRepository;

    @Autowired
    public CitaMService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public CitaDTO agendarCita(CitaDTO citaDTO) {
        if (citaDTO.getEspecialidad() == null) {
            throw new BadRequestException("Se requiere la especialidad");
        }
        else if (citaDTO.getIdentificacionUsuario() == null) {
            throw new BadRequestException("Se requiere la identificación del usuario");
        }
        else if (citaDTO.getTipoUsuario() == null) {
            throw new BadRequestException("Se requiere el tipo de usuario");
        }
        else if (citaDTO.getFechaCita() == null) {
            throw new BadRequestException("Se requiere la fecha de la cita");
        }

        LocalDate fechaAgendamiento;
        switch (citaDTO.getTipoUsuario()) {
            case EPS:
                fechaAgendamiento = LocalDate.now().plusDays(10).with(nextWorkingDay());
                break;
            case POLIZA:
                fechaAgendamiento = LocalDate.now().plusDays(8).with(nextWorkingDay());
                break;
            case PARTICULAR:
                fechaAgendamiento = LocalDate.now().plusDays(7).with(nextWorkingDay());
                break;
            default:
                throw new BadRequestException("Tipo de usuario no permitido en el hospital");
        }

        Cita cita = new Cita(
                citaDTO.getId(),
                citaDTO.getEspecialidad(),
                citaDTO.getIdentificacionUsuario(),
                citaDTO.getTipoUsuario(),
                fechaAgendamiento
        );

        citaRepository.save(cita);
        return new CitaDTO(
                cita.getId(),
                cita.getEspecialidad(),
                cita.getIdentificacionUsuario(),
                cita.getTipoUsuario(),
                cita.getFechaCita()
        );
    }

    private TemporalAdjuster nextWorkingDay() {
        return temporal -> {
            LocalDate date = (LocalDate) temporal;
            switch (date.getDayOfWeek()) {
                case SATURDAY:
                    return date.plusDays(2);
                case SUNDAY:
                    return date.plusDays(1);
                default:
                    return date;
            }
        };
    }

    public CitaDTO obtenerCita(Long idCita) {
        Optional<Cita> citaOptional = citaRepository.findById(idCita);
        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            CitaDTO citaDTO = new CitaDTO(
                    cita.getId(),
                    cita.getEspecialidad(),
                    cita.getIdentificacionUsuario(),
                    cita.getTipoUsuario(),
                    cita.getFechaCita()
            );
            return citaDTO;
        }
        else {
            throw new NotFoundException("No se encontró la cita con el ID " + idCita);
        }

    }
}