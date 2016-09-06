package com.jcodee.mod2class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class1.modelos.Combo;
import com.jcodee.mod2class1.modelos.Datos;

import java.util.ArrayList;

import butterknife.BindView;

public class RegistroActivity extends BaseActivity {
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etNombreCompleto)
    EditText etNombreCompleto;
    @BindView(R.id.etContrasena)
    EditText etContrasena;
    @BindView(R.id.etRepContrasena)
    EditText etRepContrasena;
    @BindView(R.id.spGenero)
    Spinner spGenero;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;
    private ArrayList<Combo> datosGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Obtenemos los datos de genero desde la base de datos
        datosGenero = querys.obtenerGenero();
        //Creamos un arrayList de tipo string para obtener solo los textos de genero
        ArrayList<String> generos = new ArrayList<>();
        //Recorremos la lista de datos generos para obtener solamente la descirpcion
        for (Combo combo : datosGenero) {
            //Agregamos la descripcion a nuestro spinner
            generos.add(combo.getDescripcion());
        }
        //Armamos el adapter que cargara los datos a nuestro spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                generos
        );
        //Cargamos la nueva configuración a nuestro spinner
        spGenero.setAdapter(arrayAdapter);
        btnRegistrar.setTag(-1);

        if (getIntent().hasExtra("posicion")) {
            int posicion = getIntent().getIntExtra("posicion", -1);
            Datos datos = ListadoActivity.lista.get(posicion);
            etNombreCompleto.setText(datos.getNombre());
            etEmail.setText(datos.getCorreo());
            for (int i = 0; i < datosGenero.size(); i++) {
                if (datosGenero.get(i).getId() == datos.getId()) {
                    spGenero.setSelection(i);
                    break;
                }
            }
            btnRegistrar.setTag(datos.getId());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valorButton = (int) btnRegistrar.getTag();
                //Obtenemos los datos de los componentes
                String correo = etEmail.getText().toString(),
                        nombre = etNombreCompleto.getText().toString(),
                        contrasena = etContrasena.getText().toString(),
                        repcontrasena = etRepContrasena.getText().toString();
                if (valorButton == -1) {
                    //Validamos que todos los datos esten cargados
                    if (correo.trim().length() > 0 &&
                            nombre.trim().length() > 0 &&
                            contrasena.trim().length() > 0 &&
                            repcontrasena.trim().length() > 0) {
                        //Validamos que las contraseñas sean iguales
                        if (contrasena.equals(repcontrasena)) {
                            //Creamos una variable para el registro del dato
                            Datos datos = new Datos();
                            datos.setNombre(nombre);
                            datos.setCorreo(correo);
                            String genero = spGenero.getSelectedItem().toString();
                            for (Combo combo : datosGenero) {
                                if (genero.equals(combo.getDescripcion())) {
                                    datos.setIdGenero(combo.getId());
                                    break;
                                }
                            }
                            datos.setContrasena(contrasena);
                            //Registramos el dato en la bd
                            boolean resultado = querys.registrarDatos(datos);
                            //Obtenemos el resultado de exito o no
                            if (resultado) {
                                etEmail.setText("");
                                etNombreCompleto.setText("");
                                etContrasena.setText("");
                                etRepContrasena.setText("");
                                etEmail.requestFocus();
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistroActivity.this, "No se pudo registrar.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistroActivity.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistroActivity.this, "Todos los campos son requeridos.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Actualizar datos
                    Datos datos = new Datos();
                    datos.setNombre(nombre);
                    datos.setCorreo(correo);
                    String genero = spGenero.getSelectedItem().toString();
                    for (Combo combo : datosGenero) {
                        if (genero.equals(combo.getDescripcion())) {
                            datos.setIdGenero(combo.getId());
                            break;
                        }
                    }
                    datos.setContrasena(contrasena);
                    querys.actualizarDato(datos);
                    finish();
                }
            }
        });
    }
}
