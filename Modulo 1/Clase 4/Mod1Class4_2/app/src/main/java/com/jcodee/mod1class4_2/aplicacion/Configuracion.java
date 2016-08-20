package com.jcodee.mod1class4_2.aplicacion;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 11:34
 */
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
