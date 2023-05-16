package com.remolina.workshop.dto;

import com.remolina.workshop.model.TipoUsuario;

import java.time.LocalDate;

public class CitaDTO {
    private Long id;
    private String especialidad;
    private String identificacionUsuario;
    private TipoUsuario tipoUsuario;
    private LocalDate fechaCita;

    public CitaDTO(Long id, String especialidad, String identificacionUsuario, TipoUsuario tipoUsuario, LocalDate fechaCita) {
        this.id = id;
        this.especialidad = especialidad;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaCita = fechaCita;
    }

    public CitaDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }
}
