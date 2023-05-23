package com.remolina.workshop;

import com.remolina.workshop.dto.CitaDTO;
import com.remolina.workshop.exception.BadRequestException;
import com.remolina.workshop.model.TipoUsuario;
import com.remolina.workshop.repository.CitaRepository;
import com.remolina.workshop.service.CitaMService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
    public class CitaMServiceTest {

        @Mock
        private CitaRepository citaRepository;

        @InjectMocks
        private CitaMService citaMService;

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenEspecialidadIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setIdentificacionUsuario("123456");
            citaDTO.setTipoUsuario(TipoUsuario.EPS);
            citaDTO.setFechaCita(LocalDate.now().plusDays(10));

            citaMService.agendarCita(citaDTO);
        }

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenIdentificacionUsuarioIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setEspecialidad("Cardiología");
            citaDTO.setTipoUsuario(TipoUsuario.EPS);
            citaDTO.setFechaCita(LocalDate.now().plusDays(10));

            citaMService.agendarCita(citaDTO);
        }

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenTipoUsuarioIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setEspecialidad("Cardiología");
            citaDTO.setIdentificacionUsuario("123456");
            citaDTO.setFechaCita(LocalDate.now().plusDays(10));

            citaMService.agendarCita(citaDTO);
        }

        @Test(expected = BadRequestException.class)
        public void agendarCita_shouldThrowBadRequestException_whenFechaCitaIsNull() {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setEspecialidad("Cardiología");
            citaDTO.setIdentificacionUsuario("123456");
            citaDTO.setTipoUsuario(TipoUsuario.EPS);

            citaMService.agendarCita(citaDTO);
        }
        
        @Test public void agendarCita_shouldSucceed() {
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setEspecialidad("Cardiología");
        citaDTO.setIdentificacionUsuario("123456");
        citaDTO.setTipoUsuario(TipoUsuario.EPS);
        citaDTO.setFechaCita(LocalDate.now().plusDays(10));

        // Realizar la llamada al método agendarCita y obtener el resultado
        CitaDTO resultadoCita = citaMService.agendarCita(citaDTO);

        // Verificar que el resultado no sea nulo
        Assert.assertNotNull(resultadoCita);
        Assert.assertEquals("Cardiología", resultadoCita.getEspecialidad());
        Assert.assertEquals("123456", resultadoCita.getIdentificacionUsuario());
        Assert.assertEquals(TipoUsuario.EPS, resultadoCita.getTipoUsuario());
        Assert.assertEquals(LocalDate.now().plusDays(10), resultadoCita.getFechaCita());
    }



}
