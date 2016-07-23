package com.jcodee.mod1class2_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod1class2_2.modelos.Producto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spProducto;
    private EditText etCantidad, etPrecio;
    private Button btnRegistrar, btnListado;
    public static ArrayList<Producto> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spProducto = (Spinner) findViewById(R.id.spProducto);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        btnListado = (Button) findViewById(R.id.btnListado);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Lista de datos para cargar en el spinner
        ArrayList<String> productos = new ArrayList<>();
        productos.add("Plumon");
        productos.add("Motta");
        productos.add("Control Remoto");

        //Estamos creando cada item del spinner
        //android.R.layout.simple_spinner_dropdown_item es el diseño del item del spinner que por defecto tiene android
        /*ArrayAdapter arrayAdapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                productos
        );*/
        //Cargamos el spinner desde el recurso arrays
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.productos)
        );
        //Cargamos los datos a nuestro spinner
        spProducto.setAdapter(arrayAdapter);


        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos los valores de los componentes
                String producto = spProducto.getSelectedItem().toString(),
                        cantidad = etCantidad.getText().toString(),
                        precio = etPrecio.getText().toString();

                //Validamos que todos los datos hayan sido ingresados
                if (producto.trim().length() > 0 &&
                        cantidad.trim().length() > 0 &&
                        precio.trim().length() > 0) {

                    //Creamos un objeto de tipo producto
                    Producto producto1 = new Producto();
                    //Setteamos los datos
                    producto1.setNombre(producto);
                    producto1.setCantidad(Integer.parseInt(cantidad));
                    producto1.setPrecio(Double.parseDouble(precio));
                    //Agregamos el producto a la lista
                    lista.add(producto1);

                    Toast.makeText(MainActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                    //Limpiamos todos los componentes
                    spProducto.setSelection(0);
                    etCantidad.setText("");
                    etPrecio.setText("");
                    etCantidad.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
