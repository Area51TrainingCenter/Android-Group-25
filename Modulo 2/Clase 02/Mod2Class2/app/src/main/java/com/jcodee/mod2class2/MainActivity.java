package com.jcodee.mod2class2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class2.entities.Edificio;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends BaseActivity {

    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.tilNombre)
    TextInputLayout tilNombre;
    @BindView(R.id.etDireccion)
    EditText etDireccion;
    @BindView(R.id.tilDireccion)
    TextInputLayout tilDireccion;
    @BindView(R.id.etAmbientes)
    EditText etAmbientes;
    @BindView(R.id.tilAmbientes)
    TextInputLayout tilAmbientes;
    @BindView(R.id.etPrecio)
    EditText etPrecio;
    @BindView(R.id.tilPrecio)
    TextInputLayout tilPrecio;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.btnListado)
    Button btnListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @OnClick(R.id.btnGuardar)
    public void onClick() {
        final String nombre = etNombre.getText().toString(),
                direccion = etDireccion.getText().toString(),
                ambientes = etAmbientes.getText().toString(),
                precio = etPrecio.getText().toString();

        //Validaciones
        if (nombre.trim().length() == 0) {
            tilNombre.setError("Campo requerido");
            return;
        } else {
            tilNombre.setErrorEnabled(false);
        }
        if (direccion.trim().length() == 0) {
            tilDireccion.setError("Campo requerido");
            return;
        } else {
            tilDireccion.setErrorEnabled(false);
        }
        if (ambientes.trim().length() == 0) {
            tilAmbientes.setError("Campo requerido");
            return;
        } else {
            tilAmbientes.setErrorEnabled(false);
        }
        if (precio.trim().length() == 0) {
            tilPrecio.setError("Campo requerido");
            return;
        } else {
            tilPrecio.setErrorEnabled(false);
        }

        //Obtenemos la configuración que realizamos para el proyecto
        Realm realm = Realm.getDefaultInstance();
        //Ejecutamos unas transacción
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //Creamos un nuevo objeto o un nuevo registro para la base de datos
                Edificio edificio = realm.createObject(Edificio.class);
                edificio.setId(Edificio.getUltimoId());
                edificio.setNombre(nombre);
                edificio.setDescripcion(direccion);
                edificio.setAmbiente(Integer.parseInt(ambientes));
                edificio.setPrecio(Double.parseDouble(precio));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //Si la transacción se realizo exitosamente
                Toast.makeText(MainActivity.this,
                        "Se registro correctamente.", Toast.LENGTH_SHORT).show();

                etNombre.setText("");
                etDireccion.setText("");
                etAmbientes.setText("");
                etPrecio.setText("");
                etNombre.requestFocus();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                //Si la transacción tuvo un error
                Toast.makeText(MainActivity.this,
                        "Ocurrio un error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnListado)
    public void onClickListado() {
        Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
        startActivity(intent);
    }
}
