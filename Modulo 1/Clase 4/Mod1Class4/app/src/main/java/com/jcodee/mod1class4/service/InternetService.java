package com.jcodee.mod1class4.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 10:02
 */
public class InternetService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Obtenemos el servicio de conectividad del dispositivo
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Obtenemos el estado de la conexion de red
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //Verificar si la conexión existe y es correcta
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()) {
            Toast.makeText(InternetService.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(InternetService.this, "Conexión no existe", Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Cuando se ha detenido el servicio
        Toast.makeText(InternetService.this, "Se detuvo el service", Toast.LENGTH_SHORT).show();
    }
}
