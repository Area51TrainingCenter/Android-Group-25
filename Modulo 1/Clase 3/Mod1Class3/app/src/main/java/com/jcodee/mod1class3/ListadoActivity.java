package com.jcodee.mod1class3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.jcodee.mod1class3.adapters.ProductoAdapter;
import com.jcodee.mod1class3.modelos.Producto;

import java.util.ArrayList;

/**
 * Author: johannfjs
 * Date: 6/8/16
 * Time: 10:52
 */
public class ListadoActivity extends AppCompatActivity {
    private Spinner spProducto;
    private EditText etNombre;
    private ListView lvLista;

    private ProductoAdapter adapter;

    private ArrayList<Producto> lista = new ArrayList<Producto>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        //Vinculamos las variables a nuestros componentes del xml
        spProducto = (Spinner) findViewById(R.id.spProducto);
        etNombre = (EditText) findViewById(R.id.etNombre);
        lvLista = (ListView) findViewById(R.id.lvLista);

        //Declaramos un ArrayList de string
        ArrayList<String> datos = new ArrayList<>();
        //Agregamos la opción de todos
        datos.add("Todos");
        //Obtenemos los datos del recurso arrays
        String[] dato = getResources().getStringArray(R.array.productos);
        //Recorremos los datos obtenidos del recurso arrays
        for (String item : dato) {
            //Agregamos los datos a nuestra lista
            datos.add(item);
        }

        //Cargamos los datos del recurso string-array a nuestro spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                ListadoActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                datos
        );
        //Cargamos el nuevo arreglo de opciones a nuestro spinner
        spProducto.setAdapter(arrayAdapter);

        //Vincular el adapter con nuestro listView
        adapter = new ProductoAdapter(ListadoActivity.this, MainActivity.listaProductos);
        //Cargamos el adapter
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        spProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lista.clear();
                if (position > 0) {
                    //Obtenemos el producto seleccionado
                    String producto = spProducto.getSelectedItem().toString();
                    //Creamos un arrayList para el resultado de la selección
                    //ArrayList<Producto> productos = new ArrayList<Producto>();
                    //recorremos la lista de productos que traemos desde el MainActivity
                    for (Producto item : MainActivity.listaProductos) {
                        //Si e producto seleccionado es igual al de algún elemento de la lista
                        if (producto.equals(item.getProducto())) {
                            //Agregamos el producto a la lista
                            lista.add(item);
                        }
                    }
                    //Inicializamos denuevo el listao de los productos a mostras
                    adapter = new ProductoAdapter(ListadoActivity.this, lista);
                    //cambiar el adapter o contenido de la lista
                    lvLista.setAdapter(adapter);
                } else {
                    lista.addAll(MainActivity.listaProductos);
                    //Inicializamos denuevo el listao de los productos a mostras
                    adapter = new ProductoAdapter(ListadoActivity.this, MainActivity.listaProductos);
                    //cambiar el adapter o contenido de la lista
                    lvLista.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lista.clear();
                //Obtener el nombre de la caja de texto
                String nombre = etNombre.getText().toString();
                //Validamos que el contenido del texto tenga algo
                if (nombre.length() > 0) {
                    //Creamos una lista de tipo producto nueva
                    //ArrayList<Producto> lista = new ArrayList<Producto>();
                    //Recorremos la lista de productos para verificar
                    for (Producto item : MainActivity.listaProductos) {
                        //Verificamos cuales de los productos cumplen el metodo para agregarlo
                        if (item.getNombre().contains(nombre)) {
                            //Agregamos el campo a la list nueva
                            lista.add(item);
                        }
                    }
                    //Inicializamos denuevo el listao de los productos a mostras
                    adapter = new ProductoAdapter(ListadoActivity.this, lista);
                    //cambiar el adapter o contenido de la lista
                    lvLista.setAdapter(adapter);
                } else if (nombre.length() == 0) {
                    lista.addAll(MainActivity.listaProductos);
                    //Inicializamos denuevo el listao de los productos a mostras
                    adapter = new ProductoAdapter(ListadoActivity.this, MainActivity.listaProductos);
                    //cambiar el adapter o contenido de la lista
                    lvLista.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Obtenemos el objeto de la lista según la posición
                final Producto producto = lista.get(position);

                //Construimos un alertDialog con las opciones de modificar y eliminar
                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoActivity.this);
                //Agregamos un titulo
                builder.setTitle("Mensaje");
                //Agregamos un contenido
                builder.setMessage("Acción a realizar");
                //Activar opción Actualizar
                builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Enviamos la posicion de la lista a nuestra pantalla de detalle
                        Intent intent = new Intent(ListadoActivity.this, DetalleActivity.class);
                        //cargamos los datos a enviar
                        Bundle bundle = new Bundle();
                        bundle.putInt("posicion", producto.getId());
                        intent.putExtras(bundle);
                        //Ejecutamos la sentencia
                        startActivity(intent);
                    }
                });
                //Activar opción Eliminar
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Eliminamos el item o objeto de la lista
                        MainActivity.listaProductos.remove(position);
                        //Actualizamos la lista para que muestre los datos actuales (se le indica al
                        // adapter que a habido un cambio)
                        adapter.notifyDataSetChanged();
                    }
                });
                //construimos el alertDialog
                builder.create().show();
            }
        });
    }
}
