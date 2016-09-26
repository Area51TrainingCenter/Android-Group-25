package com.jcodee.mod2class4.entities;

import io.realm.RealmObject;

/**
 * Author: johannfjs
 * Date: 17/9/16
 * Time: 12:56
 */
public class Contacto extends RealmObject {
    private String nombre;
    private String apellido;
    private String numeroCelular;
    private String rutaImagen;

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
}
