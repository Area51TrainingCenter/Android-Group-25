package com.jcodee.mod1class3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodee.mod1class3.modelos.Producto;

/**
 * Author: johannfjs
 * Date: 6/8/16
 * Time: 13:27
 */
public class DetalleActivity extends AppCompatActivity {
    private TextView tvNombre, tvProducto, tvDescuento;
    private EditText etDescripcion, etPrecio;
    private Button btnActualizar, btnCancelar;
    private int posicion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvProducto = (TextView) findViewById(R.id.tvProducto);
        tvDescuento = (TextView) findViewById(R.id.tvDescuento);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);


        //Obtenemos el bundle que enviamos desde la pantalla anterior
        Bundle bundle = getIntent().getExtras();
        //Verificamos si es que existe el key en el envio
        if (bundle.containsKey("posicion")) {
            //Obtenemos el dato del key
            posicion = bundle.getInt("posicion");
            //Obtenemos los datos del producto
            for (Producto producto : MainActivity.listaProductos) {
                if (posicion == producto.getId()) {
                    tvProducto.setText(producto.getProducto());
                    tvNombre.setText(producto.getNombre());
                    tvDescuento.setText(producto.isDescuento() ? "Si" : "No");
                    etDescripcion.setText(producto.getDescripcion());
                    etPrecio.setText(String.valueOf(producto.getPrecio()));
                    break;
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(Producto producto:MainActivity.listaProductos){
                    if(producto.getId()==posicion){
                        //Actualizamos los datos
                        producto.setDescripcion(etDescripcion.getText().toString());

                        producto.setPrecio(Double.parseDouble(etPrecio.getText().toString()));

                        //Regresamos al listado
                        finish();
                    }
                }

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish es para cerrar la pantalla en la cual estamos y regresar a la anterior
                finish();
            }
        });
    }
}
