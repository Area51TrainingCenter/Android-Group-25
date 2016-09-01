package com.jcodee.mod2class1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jcodee.mod2class1.adapters.DatoAdapter;
import com.jcodee.mod2class1.modelos.Datos;

import java.util.ArrayList;

import butterknife.BindView;

public class ListadoActivity extends BaseActivity {
    private DatoAdapter adapter;
    @BindView(R.id.lvLista)
    ListView lvLista;
    public static ArrayList<Datos> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista = querys.obtenerDatos();
        adapter = new DatoAdapter(this, lista);
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoActivity.this);
                builder.setTitle("Opciones");
                builder.setMessage("Acción a realizar");
                builder.setCancelable(false);
                builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ListadoActivity.this, RegistroActivity.class);
                        intent.putExtra("posicion", i);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = lista.get(i).getId();
                        querys.eliminarDato(id);
                        lista.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        lista = querys.obtenerDatos();
        adapter = new DatoAdapter(this, lista);
        lvLista.setAdapter(adapter);
    }
}
