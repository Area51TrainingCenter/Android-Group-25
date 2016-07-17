package com.jcodee.mod1class1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView texto;
    private EditText contenido;
    private Button presionar, validar;
    private RadioButton rbOpcion1, rbOpcion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LLL", "onCreate");

        //Vinculamos nuestra variable del xml a nuestro código java
        texto = (TextView) findViewById(R.id.tvTexto);
        contenido = (EditText) findViewById(R.id.etContenido);
        presionar = (Button) findViewById(R.id.btnPresionar);
        rbOpcion1 = (RadioButton) findViewById(R.id.rbOpcion1);
        rbOpcion2 = (RadioButton) findViewById(R.id.rbOpcion2);
        validar = (Button) findViewById(R.id.btnValidar);

        //Cambiamos el texto a mostrar de nuestro diseño
        //Obtenemos el dato de la variable hola
        String hola = getResources().getString(R.string.hola);
        //Agregamos el dato de la variable hola a nuestro componente
        texto.setText(hola);
        //Cambiamos el color de la letra
        texto.setTextColor(Color.MAGENTA);
        //Cambiamos el color de fondo
        texto.setBackgroundColor(Color.RED);
        //texto.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //Cambiamos el tamaño de la letra
        texto.setTextSize(getResources().getDimension(R.dimen.tamanio_letra_2));

        //Cambiamos el tipo de letra por uno nuestro
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        //Cambiamos a nuestra variable tvTexto el tipo de letra
        texto.setTypeface(typeface);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LLL", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LLL", "onResume");

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Declaramos una variable donde irá el dato
                String dato = "";
                //Verificamos si es que la opción 1 está seleccionada
                if (rbOpcion1.isChecked())
                    //Si está seleccionada cambiamos el valor de la variable de dato
                    dato = rbOpcion1.getText().toString();
                    //Verificamos si es que la opción 2 está seleccionada
                else if (rbOpcion2.isChecked())
                    //Si está seleccionada cambiamos el valor de la variable de dato
                    dato = rbOpcion2.getText().toString();

                //Creamos un mensaje de tipo Alert
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //Asignamos un titulo a la alerta
                builder.setTitle("Mensaje");
                //Asignamos un mensaje a la alerta
                builder.setMessage(dato);
                //Creamos la alerta y lo mostramos
                builder.create().show();

            }
        });

        rbOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos el texto de la opción
                String texto = rbOpcion1.getText().toString();
                //Mostramos el texto de la opción en un mensaje flotante
                Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();

            }
        });
        rbOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos el texto de la opción
                String texto = rbOpcion2.getText().toString();
                //Mostramos el texto de la opción en un mensaje flotante
                Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();

            }
        });

        //Creamos el metodo de click al boton presionar
        presionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos el dato que se ha ingresado en nuestra caja de texto
                String dato = contenido.getText().toString();

                if (dato.trim().length() > 0) {
                    contenido.setError(null);
                    //Creamos el mensaje flotante
                    //Toast.makeText(MainActivity.this, dato, Toast.LENGTH_SHORT).show();
                } else {
                    String dato_requerido = getResources().getString(R.string.dato_requerido);
                    contenido.setError(dato_requerido);
                    //Toast.makeText(MainActivity.this, "Datos incompletos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LLL", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LLL", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LLL", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LLL", "onRestart");
    }
}
