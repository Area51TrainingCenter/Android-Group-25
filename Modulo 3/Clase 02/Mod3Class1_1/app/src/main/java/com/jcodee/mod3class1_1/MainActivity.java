package com.jcodee.mod3class1_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jcodee.mod3class1_1.adapters.PublicacionAdapter;
import com.jcodee.mod3class1_1.aplicacion.Configuracion;
import com.jcodee.mod3class1_1.models.Publicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btnPost, btnDelete;
    private RecyclerView rvDatos;
    private PublicacionAdapter adapter;
    private ArrayList<Publicacion> lista = new ArrayList<>();

    //https://jsonplaceholder.typicode.com/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvDatos = (RecyclerView) findViewById(R.id.rvDatos);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnDelete = (Button) findViewById(R.id.btnDelete);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                "http://jsonplaceholder.typicode.com/posts",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //recorremos el jsonArray
                        for (int i = 0; i < response.length(); i++) {
                            //Obtenemos cada elemento del Json
                            JSONObject jsonObject = response.optJSONObject(i);
                            //Obtenemos los valores que necesitamos del Json
                            String titulo = jsonObject.optString("title");
                            String cuerpo = jsonObject.optString("body");
                            //Agregamos los elementos a nuestro ArrayList o Lista
                            lista.add(new Publicacion(titulo, cuerpo));
                        }
                        //Creamos el adapter
                        adapter = new PublicacionAdapter(lista);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        rvDatos.setLayoutManager(layoutManager);
                        //Agregamos el adapter a nuestro recyclerview
                        rvDatos.setAdapter(adapter);
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

    @Override
    protected void onResume() {
        super.onResume();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.DELETE,
                        "http://jsonplaceholder.typicode.com/posts/1",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MainActivity.this, "Se elimino", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                Configuracion.getInstance().agregarRequestQueue(jsonObjectRequest);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //Se crea el jsonObject en caso se tenga que enviar datos
                    JSONObject datos = new JSONObject();
                    datos.put("body", "body");
                    datos.put("title", "title");
                    datos.put("userId", 1);

                    //Realizamos la petición hacia el servicio web
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            "http://jsonplaceholder.typicode.com/posts/",
                            datos,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(MainActivity.this, "resultado->" + response.toString(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) /*{
                        @Override
                        public String getBodyContentType() {
                            return "application/json";
                        }

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> datos = new HashMap<String, String>();
                            datos.put("body", "lallala");
                            return datos;
                        }
                    }*/;
                    Configuracion.getInstance().agregarRequestQueue(jsonObjectRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
