package com.jcodee.mod1class4_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jcodee.mod1class4_2.adapters.FotoAdapter;

public class ListadoActivity extends AppCompatActivity {
    private GridView gvDatos;
    private FotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        gvDatos = (GridView) findViewById(R.id.gvDatos);

        adapter = new FotoAdapter(ListadoActivity.this, MainActivity.listaFotos);
        gvDatos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListadoActivity.this, Detalle2Activity.class);
                intent.putExtra("posicion", i);
                startActivity(intent);
            }
        });
    }
}
