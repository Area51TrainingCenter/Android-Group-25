package com.jcodee.mod1class3.aplicacion;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Author: johannfjs
 * Date: 6/8/16
 * Time: 15:17
 */
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos la librería, para que podamos utilizarla
        Fresco.initialize(getApplicationContext());
    }
}
