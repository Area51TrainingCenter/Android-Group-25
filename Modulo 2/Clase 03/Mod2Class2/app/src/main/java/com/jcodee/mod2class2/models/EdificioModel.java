package com.jcodee.mod2class2.models;

import android.support.v7.widget.RecyclerView;

/**
 * Author: johannfjs
 * Date: 3/9/16
 * Time: 14:28
 */
public class EdificioModel {
    private int id, ambientes;
    private String nombre, direccion;
    private Double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
