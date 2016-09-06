package com.jcodee.mod2class2.entities;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Author: johannfjs
 * Date: 3/9/16
 * Time: 13:46
 */
public class Edificio extends RealmObject {
    @PrimaryKey
    private long id;
    private String nombre;
    private String descripcion;
    private int ambiente;
    private Double precio;

    //Obtenemos el ultimo id de la tabla edificio
    public static int getUltimoId() {
        //Obtenemos la configuración que realizamos para el app
        Realm realm = Realm.getDefaultInstance();
        //Consultamos el ultimo registro en base al id
        Number ultimoId = realm.where(Edificio.class).max("id");
        //Verificamos el dato que nos devuelve
        return ultimoId == null ? 0 : (ultimoId.intValue() + 1);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(int ambiente) {
        this.ambiente = ambiente;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
