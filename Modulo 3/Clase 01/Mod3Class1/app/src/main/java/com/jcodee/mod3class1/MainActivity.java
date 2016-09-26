package com.jcodee.mod3class1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jcodee.mod3class1.database.Querys;
import com.jcodee.mod3class1.entities.User;

import org.json.JSONObject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btnLogin)
    LoginButton btnLogin;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Querys.obtenerSesion() != null) {
            Intent intent = new Intent(MainActivity.this, DatosActivity.class);
            startActivity(intent);
        }

        //Asignamos permisos que solicitamos al usuario para acceder a su información
        btnLogin.setReadPermissions("email", "user_birthday");

        //Creamos la respuesta de la consulta
        callbackManager = CallbackManager.Factory.create();

        //Se ejecuta como el evento onClick del boton
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Login Exitoso
                Toast.makeText(MainActivity.this, "onSuccess->" + loginResult.getAccessToken(),
                        Toast.LENGTH_SHORT).show();
                //Peticiones al servicio de Facebook para la obtención de datos
                //accessToken es la clave con la cual se podrá solicitar las peticiones,
                //está se crea con una combinación del id de usuario y es encriptada por facebook
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Resultado de la petición al api de facebook
                                //Log.d("TAG", "->" + object.toString());
                                String id = object.optString("id");
                                String firstName = object.optString("first_name");
                                String lastName = object.optString("last_name");
                                String email = object.optString("email");
                                String birthday = object.optString("birthday");

                                User user = new User();
                                user.setId(id);
                                user.setFirstName(firstName);
                                user.setLastName(lastName);
                                user.setEmail(email);
                                user.setBirthday(birthday);
                                Querys.registrarSesion(user);
                                Intent intent = new Intent(MainActivity.this, DatosActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, name, link, email, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                //Login cancelado por el usuario
                Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                //Error en el login
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Obtiene el resultado de la consulta realizada a otra actividad fuera de nuestra aplicación
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
