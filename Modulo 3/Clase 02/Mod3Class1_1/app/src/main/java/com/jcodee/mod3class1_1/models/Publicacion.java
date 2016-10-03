package com.jcodee.mod3class1_1.models;

/**
 * Author: johannfjs
 * Date: 1/10/16
 * Time: 10:48
 */

public class Publicacion {
    private String titulo, descripcion;

    public Publicacion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
