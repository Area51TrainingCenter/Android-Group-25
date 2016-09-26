package com.jcodee.mod3class1.aplicacion;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Author: johannfjs
 * Date: 24/9/16
 * Time: 10:43
 */
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Configuración de inicio de Realm
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder(getApplicationContext())
                        .name("mod3class1.realm")
                        .schemaVersion(1)
                        .build();
        //Cambiamos la configuración de Realm
        Realm.setDefaultConfiguration(realmConfiguration);

        //Inicializamos la librería de Fresco
        Fresco.initialize(getApplicationContext());
        //Inicializamos la librería de Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
