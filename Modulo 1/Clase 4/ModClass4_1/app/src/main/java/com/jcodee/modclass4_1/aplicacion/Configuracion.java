package com.jcodee.modclass4_1.aplicacion;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.jcodee.modclass4_1.receiver.InternetReceiver;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 10:40
 */
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Ejecutamos el receiver para que este a la escucha de la acción
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new InternetReceiver(), intentFilter);
    }
}
