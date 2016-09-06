package com.jcodee.mod2class2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodee.mod2class2.adapters.EdificioAdapter;
import com.jcodee.mod2class2.entities.Edificio;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        //Obtenemos la congiruación de realm que realizamos
        Realm realm = Realm.getDefaultInstance();
        //Obtenemos los datos de tipo edificio
        RealmResults<Edificio> results = realm.where(Edificio.class).findAll();
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
}
