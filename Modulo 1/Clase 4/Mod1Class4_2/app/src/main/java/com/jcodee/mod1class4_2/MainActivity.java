package com.jcodee.mod1class4_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jcodee.mod1class4_2.modelos.Foto;
import com.jcodee.mod1class4_2.views.ButtonCustom;
import com.jcodee.mod1class4_2.views.EditTextCustom;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ButtonCustom btnGrabar, btnListado;
    private EditTextCustom etTitulo, etDescripcion;
    private RadioButton rbFoto1, rbFoto2, rbFoto3, rbFoto4;
    public static ArrayList<Foto> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGrabar = (ButtonCustom) findViewById(R.id.btnGrabar);
        btnListado = (ButtonCustom) findViewById(R.id.btnListado);
        etTitulo = (EditTextCustom) findViewById(R.id.etTitulo);
        etDescripcion = (EditTextCustom) findViewById(R.id.etDescripcion);
        rbFoto1 = (RadioButton) findViewById(R.id.rbFoto1);
        rbFoto2 = (RadioButton) findViewById(R.id.rbFoto2);
        rbFoto3 = (RadioButton) findViewById(R.id.rbFoto3);
        rbFoto4 = (RadioButton) findViewById(R.id.rbFoto4);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titulo = etTitulo.getText().toString(),
                        descripcion = etDescripcion.getText().toString();
                if (titulo.trim().length() > 0 &&
                        descripcion.trim().length() > 0) {

                    Foto item = new Foto();
                    item.setTitulo(titulo);
                    item.setDescripcion(descripcion);
                    String rutaFoto = "";
                    if (rbFoto1.isChecked()) {
                        rutaFoto = "http://www.paisajesbonitos.org/wp-content/uploads/2015/11/paisajes-bonitos-de-oto%C3%B1o-lago.jpg";
                    } else if (rbFoto2.isChecked()) {
                        rutaFoto = "http://www.365imagenesbonitas.com/wp-content/uploads/2014/07/paisaje-colombia-300x225.jpg";
                    } else if (rbFoto3.isChecked()) {
                        rutaFoto = "https://i.ytimg.com/vi/GjiFHJNSqQI/maxresdefault.jpg";
                    } else if (rbFoto4.isChecked()) {
                        rutaFoto = "https://i.ytimg.com/vi/NB2c5GW49XE/maxresdefault.jpg";
                    }
                    item.setRutaFoto(rutaFoto);
                    listaFotos.add(item);
                    Toast.makeText(MainActivity.this, "Se grabo correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listaFotos.size() > 0) {
                    Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Debe de registrar una Foto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rbFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbFoto2.setChecked(false);
                rbFoto3.setChecked(false);
                rbFoto4.setChecked(false);
            }
        });
        rbFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbFoto1.setChecked(false);
                rbFoto3.setChecked(false);
                rbFoto4.setChecked(false);
            }
        });
        rbFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbFoto2.setChecked(false);
                rbFoto1.setChecked(false);
                rbFoto4.setChecked(false);
            }
        });
        rbFoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbFoto2.setChecked(false);
                rbFoto3.setChecked(false);
                rbFoto1.setChecked(false);
            }
        });
    }
}
