package com.jcodee.mod1class3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod1class3.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Declaración de variables
    private Spinner spProducto;
    private EditText etNombre, etDescripcion, etPrecio;
    private RadioButton rbSi, rbNo;
    private Button btnAgregar, btnListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización o vinculación de variable a componente
        spProducto = (Spinner) findViewById(R.id.spProducto);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        rbSi = (RadioButton) findViewById(R.id.rbSi);
        rbNo = (RadioButton) findViewById(R.id.rbNo);
        btnAgregar = (Button) findViewById(R.id.btnGrabar);
        btnListado = (Button) findViewById(R.id.btnListar);

        //Cargamos los datos del recurso string-array a nuestro spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.productos)
        );
        //Cargamos el nuevo arreglo de opciones a nuestro spinner
        spProducto.setAdapter(arrayAdapter);
    }

    public static ArrayList<Producto> listaProductos = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos los datos de todos los componentes
                String producto = spProducto.getSelectedItem().toString(),
                        nombre = etNombre.getText().toString(),
                        descripcion = etDescripcion.getText().toString(),
                        precio = etPrecio.getText().toString();
                boolean descuento = rbSi.isChecked() ? true : rbNo.isChecked() ? false : null;

                //Validamos que todos los campos esten llenos
                if (producto.length() > 0 &&
                        nombre.length() > 0 &&
                        descripcion.length() > 0 &&
                        precio.length() > 0) {
                    //Guardamos los datos en una lista temporal
                    Producto item = new Producto();
                    item.setId(listaProductos.size() + 1);
                    item.setNombre(nombre);
                    item.setDescripcion(descripcion);
                    item.setProducto(producto);
                    item.setPrecio(Double.parseDouble(precio));
                    item.setDescuento(descuento);
                    listaProductos.add(item);
                    Toast.makeText(MainActivity.this, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    //Sino mostramos un mensaje flotante
                    Toast.makeText(MainActivity.this, "Complete todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Metodo para poder ir a otra pantalla
                Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                startActivity(intent);
                //Si es que queremos que la pantalla no se guarde en el historial de nevegación
                //finish();
            }
        });
    }
}
