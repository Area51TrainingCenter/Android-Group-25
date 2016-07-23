package com.jcodee.mod1class2_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Author: johannfjs
 * Date: 23/7/16
 * Time: 14:30
 */
public class FinalActivity extends AppCompatActivity {
    private Button btnCerrarTodo, btnTransformar;
    private EditText etTexto;
    private TextView tvTexto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        btnCerrarTodo = (Button) findViewById(R.id.btnCerrarTodo);
        btnTransformar = (Button) findViewById(R.id.btnTransformar);
        etTexto = (EditText) findViewById(R.id.etTexto);
        tvTexto = (TextView) findViewById(R.id.tvTexto);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnTransformar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos el texto del edittext
                String texto = etTexto.getText().toString();
                //Interpretamos el texto html
                tvTexto.setText(Html.fromHtml(texto));

            }
        });

        btnCerrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Limpia el historial de pantallas
                Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                //Limpia desde el inicio
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //Limpia las tareas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //Crea una nueva tarea
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}
