package com.jcodee.mod2class1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class1.sqlite.Querys;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etContrasena)
    EditText etContrasena;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verificamos si es que existe la sesión
        String nombreCompleto = querys.existeSesion();
        if (nombreCompleto.length() > 0) {
            Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
            intent.putExtra("nombre", nombreCompleto);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos los datos de los componentes
                String correo = etEmail.getText().toString(),
                        contrasena = etContrasena.getText().toString();
                //Validamos los datos
                if (correo.trim().length() > 0 &&
                        contrasena.trim().length() > 0) {
                    //Ejecutamos el metodo de la base de datos
                    boolean resultado = querys.validarUsuario(correo, contrasena);
                    if (resultado) {
                        //Insertamos la sesión
                        querys.insertarSesion(correo);
                        //Enviamos a la pantalla 3
                        Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,
                            "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pasamos a la pantalla del Registro
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
