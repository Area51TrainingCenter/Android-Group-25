package com.jcodee.mod1class2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Author: johannfjs
 * Date: 23/7/16
 * Time: 11:02
 */
public class MostrarActivity extends AppCompatActivity {
    private TextView tvNombre, tvTipo, tvGenero;
    private Button btnAtras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTipo = (TextView) findViewById(R.id.tvTipo);
        tvGenero = (TextView) findViewById(R.id.tvGenero);
        btnAtras = (Button) findViewById(R.id.btnAtras);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Preguntamos si es que existe el key de nombre en los datos enviados desde la página 2
        if (getIntent().hasExtra("nombre")) {
            //Obtenemos el dato enviado a través de su key
            String nombre = getIntent().getStringExtra("nombre");
            //Setteamos el TextView que tenemos
            tvNombre.setText(nombre);
        }
        if (getIntent().hasExtra("tipo")) {
            String tipo = getIntent().getStringExtra("tipo");
            tvTipo.setText(tipo);
        }
        if (getIntent().hasExtra("genero")) {
            String genero = getIntent().getStringExtra("genero");
            tvGenero.setText(genero);
        }

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sirve para retroceder en pantallas y borrar el historial de la pantalla
                finish();
            }
        });
    }
}
