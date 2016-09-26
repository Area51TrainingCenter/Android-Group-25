package com.jcodee.mod2class4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodee.mod2class4.database.Querys;
import com.jcodee.mod2class4.entities.Contacto;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends BaseActivity {

    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.tilNombre)
    TextInputLayout tilNombre;
    @BindView(R.id.etApellido)
    EditText etApellido;
    @BindView(R.id.tilApellido)
    TextInputLayout tilApellido;
    @BindView(R.id.etCelular)
    EditText etCelular;
    @BindView(R.id.tilCelular)
    TextInputLayout tilCelular;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;
    @BindView(R.id.btnListado)
    Button btnListado;
    @BindView(R.id.btnRegistrarShared)
    Button btnRegistrarShared;
    @BindView(R.id.btnEliminarShared)
    Button btnEliminarShared;
    @BindView(R.id.tvResultado)
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        obtenerDatos();
    }

    @OnClick({R.id.btnRegistrar, R.id.btnListado, R.id.btnRegistrarShared, R.id.btnEliminarShared})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegistrar:

                Contacto contacto = new Contacto();
                contacto.setNombre(etNombre.getText().toString());
                contacto.setApellido(etApellido.getText().toString());
                contacto.setNumeroCelular(etCelular.getText().toString());
                boolean result = Querys.registrarContacto(contacto);
                if (result)
                    Toast.makeText(RegistroActivity.this, "OK", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RegistroActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnListado:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRegistrarShared:

                //Creamos un shared preference
                SharedPreferences sharedPreferences = getSharedPreferences("Mod2Class4", MODE_PRIVATE);
                //Habilitamos el modo de edición del shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nombre", etNombre.getText().toString());
                editor.putString("apellido", etApellido.getText().toString());
                editor.putString("celular", etCelular.getText().toString());
                editor.commit();
                Toast.makeText(RegistroActivity.this, "Se grabo", Toast.LENGTH_SHORT).show();
                obtenerDatos();

                break;
            case R.id.btnEliminarShared:

                SharedPreferences sharedPreferences1 = getSharedPreferences("Mod2Class4", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                editor1.clear();
                editor1.commit();
                Toast.makeText(RegistroActivity.this, "Se elimino", Toast.LENGTH_SHORT).show();
                obtenerDatos();

                break;
        }
    }

    private void obtenerDatos() {
        SharedPreferences sharedPreferences1 = getSharedPreferences("Mod2Class4", MODE_PRIVATE);
        tvResultado.setText(
                "Nombre: " + sharedPreferences1.getString("nombre", "") +
                        " Apellido: " + sharedPreferences1.getString("apellido", "") +
                        " Celular: " + sharedPreferences1.getString("celular", "")
        );
    }
}
