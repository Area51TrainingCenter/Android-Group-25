package com.jcodee.mod2class4.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.adapter.LlamadaAdapter;
import com.jcodee.mod2class4.database.Querys;
import com.jcodee.mod2class4.entities.Llamada;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class LlamadaFragment extends Fragment {

    @BindView(R.id.rvDatos)
    RecyclerView rvDatos;

    private LlamadaAdapter adapter;
    private ArrayList<Llamada> lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llamada, container, false);
        ButterKnife.bind(this, view);

        lista = new ArrayList<>();
        RealmResults<Llamada> results = Querys.obtenerLlamadas();
        for (Llamada llamada : results) {
            lista.add(llamada);
        }

        adapter = new LlamadaAdapter(lista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvDatos.setLayoutManager(layoutManager);
        rvDatos.setAdapter(adapter);

        return view;
    }

}
