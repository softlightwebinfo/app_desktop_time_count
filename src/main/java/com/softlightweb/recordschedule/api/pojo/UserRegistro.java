/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softlightweb.recordschedule.api.pojo;

import java.time.*;

/**
 *
 * @author toni
 */
public class UserRegistro {

    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private tipoRegistro tipo;
    
    public UserRegistro(){};
    
    /*GETTER*/

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public tipoRegistro getTipo() {
        return tipo;
    }
    
    /*SETTER*/
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setTipo(tipoRegistro tipo) {
        this.tipo = tipo;
    }    
}
