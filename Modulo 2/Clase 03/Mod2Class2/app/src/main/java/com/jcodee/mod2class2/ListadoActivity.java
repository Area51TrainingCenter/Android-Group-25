package com.jcodee.mod2class2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class2.adapters.EdificioAdapter;
import com.jcodee.mod2class2.entities.Edificio;
import com.jcodee.mod2class2.events.ClickListener;
import com.jcodee.mod2class2.events.RecyclerTouchListener;
import com.jcodee.mod2class2.models.EdificioModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListadoActivity extends BaseActivity {
    @BindView(R.id.rvLista)
    RecyclerView rvLista;
    private ArrayList<EdificioModel> lista = new ArrayList<>();
    private EdificioAdapter adapter;
    private RealmResults<Edificio> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        //Obtenemos la congiruación de realm que realizamos
        Realm realm = Realm.getDefaultInstance();
        //Obtenemos los datos de tipo edificio
        results = realm.where(Edificio.class).findAll();
        //Recorremos los resultados
        for (Edificio item : results) {
            //Creamos un objeto de tipo edificioModel para capturar los datos
            EdificioModel edificioModel = new EdificioModel();
            edificioModel.setId(Integer.parseInt(String.valueOf(item.getId())));
            edificioModel.setNombre(item.getNombre());
            edificioModel.setAmbientes(item.getAmbiente());
            edificioModel.setDireccion(item.getDescripcion());
            edificioModel.setPrecio(item.getPrecio());
            lista.add(edificioModel);
        }
        //Inicializamos el adapter
        adapter = new EdificioAdapter(lista);
        //Configuramos como se posicionaran los elementos
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListadoActivity.this);
        rvLista.setLayoutManager(layoutManager);
        //Cambiamos el adapter del recyclerView por el nuestro
        rvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        rvLista.addOnItemTouchListener(new RecyclerTouchListener(
                getApplicationContext(),
                rvLista,
                new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListadoActivity.this);
                        builder.setTitle("Opciones");
                        //Bloquea todas las opciones y solo puedes seleccionar las que se tiene en el popup
                        builder.setCancelable(false);
                        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                buildUpdate(position);

                                dialogInterface.dismiss();
                            }
                        });
                        builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Realm realm = Realm.getDefaultInstance();
                                realm.beginTransaction();

                                Edificio edificio = results.get(position);
                                edificio.deleteFromRealm();

                                realm.commitTransaction();

                                lista.remove(position);
                                adapter.notifyDataSetChanged();

                                dialogInterface.dismiss();
                            }
                        });
                        builder.create().show();

                        /*Toast.makeText(ListadoActivity.this,
                                "posicion->" + lista.get(position).getNombre(),
                                Toast.LENGTH_SHORT).show();*/
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));
    }

    private void buildUpdate(final int position) {
        final Dialog dialog = new Dialog(ListadoActivity.this);
        dialog.setContentView(R.layout.item_modificar);

        final Edificio edificio = results.get(position);

        final EditText etNombre, etDireccion;
        Button btnModificar;

        etNombre = (EditText) dialog.findViewById(R.id.etNombre);
        etDireccion = (EditText) dialog.findViewById(R.id.etDireccion);
        btnModificar = (Button) dialog.findViewById(R.id.btnModificar);


        etNombre.setText(edificio.getNombre());
        etDireccion.setText(edificio.getDescripcion());

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                edificio.setNombre(etNombre.getText().toString());
                edificio.setDescripcion(etDireccion.getText().toString());
                realm.copyToRealmOrUpdate(edificio);

                realm.commitTransaction();

                lista.get(position).setNombre(etNombre.getText().toString());
                lista.get(position).setDireccion(etDireccion.getText().toString());
                adapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
