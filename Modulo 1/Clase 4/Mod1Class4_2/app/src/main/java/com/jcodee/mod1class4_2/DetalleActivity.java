package com.jcodee.mod1class4_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod1class4_2.modelos.Foto;
import com.jcodee.mod1class4_2.zoomable.ZoomableDraweeView;

public class DetalleActivity extends AppCompatActivity {
    private ZoomableDraweeView sdvImagen;
    private TextView tvTitulo;
    private Button btnDescripcion;
    private int posicion = -1;
    private String descripcion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        sdvImagen = (ZoomableDraweeView) findViewById(R.id.sdvImagen);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        btnDescripcion = (Button) findViewById(R.id.btnDescripcion);

        if (getIntent().hasExtra("posicion")) {
            posicion = getIntent().getIntExtra("posicion", -1);
            if (posicion >= 0) {
                Foto item = MainActivity.listaFotos.get(posicion);
                //sdvImagen.setImageURI(Uri.parse(item.getRutaFoto()));


                final AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse(item.getRutaFoto()))
                        .setOldController(sdvImagen.getController())
                        .build();

                sdvImagen.setController(controller);

                tvTitulo.setText(item.getTitulo());
                descripcion = item.getDescripcion();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creamos un dialog customizado por nosotros
                final Dialog dialog = new Dialog(DetalleActivity.this);
                //Asignamos el diseño al dialogo
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alert);
                dialog.setCancelable(false);

                //Declaramos las variables a utilizar en el dialog
                TextView tvDescripcion = (TextView) dialog.findViewById(R.id.tvDescripcion);
                Button btnCerrar = (Button) dialog.findViewById(R.id.btnCerrar);

                tvDescripcion.setText(descripcion);

                btnCerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
