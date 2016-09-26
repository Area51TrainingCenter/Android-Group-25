package com.jcodee.mod2class4.database;

import com.jcodee.mod2class4.entities.Contacto;
import com.jcodee.mod2class4.entities.Llamada;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Author: johannfjs
 * Date: 17/9/16
 * Time: 13:24
 */
public class Querys {

    public static boolean registrarContacto(Contacto contacto) {
        boolean resultado = false;
        try {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            Contacto item = realm.createObject(Contacto.class);
            item.setNombre(contacto.getNombre());
            item.setApellido(contacto.getApellido());
            item.setNumeroCelular(contacto.getNumeroCelular());
            item.setRutaImagen("http://www.pcutilitiespro.com/blog/wp-content/uploads/2014/04/crazy-android-tips-817x350-817x350.jpg");

            realm.commitTransaction();
            resultado = true;
        } catch (Exception e) {

        }
        return resultado;
    }

    public static RealmResults<Contacto> obtenerContactos() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Contacto> lista = realm.where(Contacto.class).findAll();
        return lista;
    }

    public static boolean registrarLlamada(Llamada llamada) {
        boolean resultado = false;
        try {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            Llamada item = realm.createObject(Llamada.class);
            item.setNumeroCelular(llamada.getNumeroCelular());
            item.setFechaHora(new Date().toString());

            realm.commitTransaction();
            resultado = true;
        } catch (Exception e) {

        }
        return resultado;
    }

    public static RealmResults<Llamada> obtenerLlamadas() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Llamada> lista = realm.where(Llamada.class).findAll();
        return lista;
    }
}
