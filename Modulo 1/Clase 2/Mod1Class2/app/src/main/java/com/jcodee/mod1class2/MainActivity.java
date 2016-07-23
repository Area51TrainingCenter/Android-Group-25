package com.jcodee.mod1class2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre;
    private Spinner spTipo;
    private RadioButton rbMasculino, rbFemenino;
    private Button btnMostrar, btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        spTipo = (Spinner) findViewById(R.id.spTipo);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        rbFemenino = (RadioButton) findViewById(R.id.rbFemenino);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener datos de los componentes
                String nombre = etNombre.getText().toString(),
                        tipo = spTipo.getSelectedItem().toString(),
                        genero = rbMasculino.isChecked() ?
                                rbMasculino.getText().toString() :
                                rbFemenino.getText().toString();

                //Enviar de una página a otra

                //Construimos el cambio de página
                Intent intent = new Intent(MainActivity.this, MostrarActivity.class);

                //Pasamos los datos a la siguiente pantalla
                intent.putExtra("nombre", nombre);
                intent.putExtra("tipo", tipo);
                intent.putExtra("genero", genero);

                //Ejecutamos el cambio de pantalla
                startActivity(intent);

            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString(),
                        tipo = spTipo.getSelectedItem().toString(),
                        genero = rbMasculino.isChecked() ?
                                rbMasculino.getText().toString() : rbFemenino.getText().toString();

                if (nombre.trim().length() > 0 &&
                        tipo.trim().length() > 0 &&
                        genero.trim().length() > 0) {
                    Toast.makeText(MainActivity.this,
                            "Nombre->" + nombre + " Tipo->" + tipo + "Genero->" + genero,
                            Toast.LENGTH_SHORT).show();
                } else {
                    /*Toast.makeText(MainActivity.this,
                            "Complete todos los campos",
                            Toast.LENGTH_SHORT).show();*/
                    //MainActivity.this es igual a getApplicationContext()
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(getResources().getString(R.string.mensaje));
                    builder.setMessage(getResources().getString(R.string.alerta));
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNeutralButton(R.string.neutral, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
            }
        });
    }
}
