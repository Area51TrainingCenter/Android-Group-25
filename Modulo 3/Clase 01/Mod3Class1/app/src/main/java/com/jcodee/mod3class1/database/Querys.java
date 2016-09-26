package com.jcodee.mod3class1.database;

import com.jcodee.mod3class1.entities.User;

import java.util.regex.Pattern;

import io.realm.Realm;

/**
 * Author: johannfjs
 * Date: 24/9/16
 * Time: 12:24
 */
public class Querys {
    public static void registrarSesion(User user) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        User item = realm.createObject(User.class);
        item.setId(user.getId());
        item.setBirthday(user.getBirthday());
        item.setEmail(user.getEmail());
        item.setFirstName(user.getFirstName());
        item.setLastName(user.getLastName());
        item.setLink(user.getLink());

        realm.commitTransaction();
    }

    public static void eliminarSesion() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.delete(User.class);

        realm.commitTransaction();
    }

    public static User obtenerSesion() {
        /*
        Pattern pattern = Pattern.compile("expresion");
        boolean valido = pattern.matcher(valor);
        */
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).findFirst();
    }
}
