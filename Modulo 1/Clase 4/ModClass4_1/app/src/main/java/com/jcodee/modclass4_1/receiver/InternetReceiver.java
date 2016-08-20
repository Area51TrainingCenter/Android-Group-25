package com.jcodee.modclass4_1.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.jcodee.modclass4_1.MainActivity;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 10:33
 */
public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Obtener el servicio de internet
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Obtenemos el estado de la red
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //Verificamos si existe conexión
        if (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()) {
            Toast.makeText(context, "Conexión exitosa", Toast.LENGTH_SHORT).show();
            //Intent intent1 = new Intent(context, MainActivity.class);
            //context.startActivity(intent1);
        } else {
            Toast.makeText(context, "No hay coneión a internet", Toast.LENGTH_SHORT).show();
        }
    }
}
