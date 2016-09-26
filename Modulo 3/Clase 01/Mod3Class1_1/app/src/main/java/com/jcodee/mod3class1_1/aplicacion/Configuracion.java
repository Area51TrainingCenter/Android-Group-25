package com.jcodee.mod3class1_1.aplicacion;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Author: johannfjs
 * Date: 24/9/16
 * Time: 14:40
 */
public class Configuracion extends Application {
    public static final String TAG = Configuracion.class.getSimpleName();
    private static Configuracion instance;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        //Obtenemos la instancia actual al momento de inicializar la aplicación
        instance = this;
    }

    //Obtener la instancia sincronizada en el momento que lo solicitamos
    public static synchronized Configuracion getInstance() {
        return instance;
    }

    //Obtener la lista de peticiones
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    //Agregar peticiones a la lista
    public <T> void agregarRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }
}
