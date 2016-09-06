package com.jcodee.mod2class1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jcodee.mod2class1.adapters.DatoAdapter;
import com.jcodee.mod2class1.modelos.Datos;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;

public class ListadoActivity extends BaseActivity {
    private DatoAdapter adapter;
    @BindView(R.id.lvLista)
    ListView lvLista;
    @BindView(R.id.btnCerrarSesion)
    Button btnCerrarSesion;
    @BindView(R.id.lblNombre)
    TextView lblNombre;
    public static ArrayList<Datos> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista = querys.obtenerDatos();
        adapter = new DatoAdapter(this, lista);
        lvLista.setAdapter(adapter);

        if (getIntent().hasExtra("nombre"))
            lblNombre.setText(getIntent().getStringExtra("nombre"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querys.eliminarSesion();

                Intent intent = new Intent(ListadoActivity.this, MainActivity.class);
                //Los Flag eliminan el historico de navegabilidad de la app
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();
            }
        });

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoActivity.this);
                builder.setTitle("Opciones");
                builder.setMessage("Acción a realizar");
                builder.setCancelable(false);
                builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ListadoActivity.this, RegistroActivity.class);
                        intent.putExtra("posicion", position);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = lista.get(position).getId();
                        querys.eliminarDato(id);
                        lista.remove(position);
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
