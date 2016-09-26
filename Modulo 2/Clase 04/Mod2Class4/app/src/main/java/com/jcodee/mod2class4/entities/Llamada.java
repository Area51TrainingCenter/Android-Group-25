package com.jcodee.mod2class4.entities;

import io.realm.RealmObject;

/**
 * Author: johannfjs
 * Date: 17/9/16
 * Time: 12:55
 */
public class Llamada extends RealmObject {
    private String numeroCelular;
    private String fechaHora;

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
}
