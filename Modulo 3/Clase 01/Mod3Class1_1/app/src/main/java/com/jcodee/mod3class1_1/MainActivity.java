package com.jcodee.mod3class1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jcodee.mod3class1_1.aplicacion.Configuracion;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private TextView tvDatos;

    //https://jsonplaceholder.typicode.com/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDatos = (TextView) findViewById(R.id.tvDatos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                "http://jsonplaceholder.typicode.com/posts",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tvDatos.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        Configuracion.getInstance().agregarRequestQueue(jsonArrayRequest);
    }
}
