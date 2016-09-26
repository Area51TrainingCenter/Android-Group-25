package com.jcodee.mod3class1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginManager;
import com.jcodee.mod3class1.database.Querys;
import com.jcodee.mod3class1.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatosActivity extends BaseActivity {

    @BindView(R.id.tvDatos)
    TextView tvDatos;
    @BindView(R.id.sdvImagen)
    SimpleDraweeView sdvImagen;
    @BindView(R.id.btnLogout)
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        User user = Querys.obtenerSesion();

        sdvImagen.setImageURI(Uri.parse(
                "https://graph.facebook.com/" + user.getId() + "/picture?type=large"
        ));

        tvDatos.setText(
                Html.fromHtml(
                        "Usuario: " + user.getEmail() +
                                "<br/>" +
                                "Nombre: " + user.getFirstName() +
                                "<br/>" +
                                "Apellido: " + user.getLastName() +
                                "<br/>" +
                                "Fecha Nacimiento: " + user.getBirthday() +
                                "<br/>" +
                                "URL: " + "<a href=\"" + user.getLink() + "\"" + ">Perfil</a>"
                )
        );
    }

    @OnClick(R.id.btnLogout)
    public void onClick() {
        LoginManager.getInstance().logOut();
        Querys.eliminarSesion();
        Intent intent = new Intent(DatosActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
