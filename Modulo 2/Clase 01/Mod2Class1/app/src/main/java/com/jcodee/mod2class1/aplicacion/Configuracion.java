package com.jcodee.mod2class1.aplicacion;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Author: johannfjs
 * Date: 27/8/16
 * Time: 13:44
 */
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());


        //Obtener la base de datos para poderla visualizar
        //data-data-<package name>-databases-<db name>
    }
}
