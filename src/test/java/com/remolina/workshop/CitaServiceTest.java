package com.remolina.workshop;

import com.remolina.workshop.dto.CitaDTO;
import com.remolina.workshop.exception.BadRequestException;
import com.remolina.workshop.model.TipoUsuario;
import com.remolina.workshop.repository.CitaRepository;
import com.remolina.workshop.service.CitaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
    public class CitaServiceTest {

        @Mock
        private CitaRepository citaRepository;

        @InjectMocks
        private CitaService citaService;

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenEspecialidadIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setIdentificacionUsuario("123456");
            citaDTO.setTipoUsuario(TipoUsuario.EPS);
            citaDTO.setFechaCita(LocalDate.now().plusDays(10));

            citaService.agendarCita(citaDTO);
        }

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenIdentificacionUsuarioIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setEspecialidad("Cardiología");
            citaDTO.setTipoUsuario(TipoUsuario.EPS);
            citaDTO.setFechaCita(LocalDate.now().plusDays(10));

            citaService.agendarCita(citaDTO);
        }

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenTipoUsuarioIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setEspecialidad("Cardiología");
            citaDTO.setIdentificacionUsuario("123456");
            citaDTO.setFechaCita(LocalDate.now().plusDays(10));

            citaService.agendarCita(citaDTO);
        }

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenFechaCitaIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setEspecialidad("Cardiología");
            citaDTO.setIdentificacionUsuario("123456");
            citaDTO.setTipoUsuario(TipoUsuario.EPS);

            citaService.agendarCita(citaDTO);
        }



}