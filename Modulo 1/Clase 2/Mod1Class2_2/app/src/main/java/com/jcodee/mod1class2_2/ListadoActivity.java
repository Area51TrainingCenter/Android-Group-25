package com.jcodee.mod1class2_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jcodee.mod1class2_2.adapters.ProductoAdapter;
import com.jcodee.mod1class2_2.modelos.Producto;

/**
 * Author: johannfjs
 * Date: 23/7/16
 * Time: 12:46
 */
public class ListadoActivity extends AppCompatActivity {
    private ProductoAdapter adapter;
    private ListView lvLista;
    private Button btnSiguiente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        //Vinculamos las variables
        lvLista = (ListView) findViewById(R.id.lvLista);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        //Inicializamos el adapter para poder obtener la lista
        adapter = new ProductoAdapter(ListadoActivity.this, MainActivity.lista);
        //Vinculamos el adapter a nuestro listView
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoActivity.this, FinalActivity.class);
                startActivity(intent);
            }
        });

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Obtenemos el producto según la selección de la lista
                Producto producto = MainActivity.lista.get(position);

                //Creamos toast
                Toast toast =
                        Toast.makeText(getApplicationContext(),
                                "Producto-> " + producto.getNombre() + "\n" +
                                        "Cantidad-> " + producto.getCantidad() + "\n" +
                                        "Precio-> " + producto.getPrecio(),
                                Toast.LENGTH_SHORT);
                //Cambiamos la posición del toast al medio
                toast.setGravity(Gravity.CENTER, 0, 0);
                //Ejecutamos el toast
                toast.show();

            }
        });
    }
}
