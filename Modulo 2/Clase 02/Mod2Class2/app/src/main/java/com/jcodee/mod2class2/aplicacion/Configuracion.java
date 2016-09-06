package com.jcodee.mod2class2.aplicacion;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Author: johannfjs
 * Date: 3/9/16
 * Time: 13:41
 */
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Creamos la configuración para nuestro proyecto
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder(getApplicationContext())
                        .name("mod2class2.realm")
                        .build();
        //Añadimos la configuración a nuestro proyecto
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
