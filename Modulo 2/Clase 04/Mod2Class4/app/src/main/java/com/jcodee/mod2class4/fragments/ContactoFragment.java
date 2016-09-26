package com.jcodee.mod2class4.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.adapter.ContactoAdapter;
import com.jcodee.mod2class4.database.Querys;
import com.jcodee.mod2class4.entities.Contacto;
import com.jcodee.mod2class4.entities.Llamada;
import com.jcodee.mod2class4.events.ClickListener;
import com.jcodee.mod2class4.events.RecyclerTouchListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactoFragment extends Fragment {

    @BindView(R.id.rvDatos)
    RecyclerView rvDatos;

    private ContactoAdapter adapter;
    private ArrayList<Contacto> lista;

    public ContactoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);
        ButterKnife.bind(this, view);

        lista = new ArrayList<>();
        RealmResults<Contacto> results = Querys.obtenerContactos();
        for (Contacto contacto : results) {
            lista.add(contacto);
        }

        adapter = new ContactoAdapter(lista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvDatos.setLayoutManager(layoutManager);
        rvDatos.setAdapter(adapter);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        rvDatos.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(),
                rvDatos,
                new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(Intent.ACTION_CALL,
                                Uri.parse("tel:" + lista.get(position).getNumeroCelular()));
                        startActivity(intent);
                        Llamada llamada = new Llamada();
                        llamada.setNumeroCelular(lista.get(position).getNumeroCelular());
                        Querys.registrarLlamada(llamada);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));
    }
}
