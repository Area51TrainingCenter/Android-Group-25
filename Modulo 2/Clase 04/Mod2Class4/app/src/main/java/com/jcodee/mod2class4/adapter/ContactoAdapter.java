package com.jcodee.mod2class4.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.entities.Contacto;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: johannfjs
 * Date: 17/9/16
 * Time: 13:07
 */
public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.MyViewHolder> {
    private ArrayList<Contacto> lista;

    public ContactoAdapter(ArrayList<Contacto> lista) {
        this.lista = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contacto contacto = lista.get(position);
        holder.sdvImagen.setImageURI(Uri.parse(contacto.getRutaImagen()));
        holder.tvNombre.setText(contacto.getNombre());
        holder.tvApellido.setText(contacto.getApellido());
        holder.tvNumeroCelular.setText(contacto.getNumeroCelular());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdvImagen)
        SimpleDraweeView sdvImagen;
        @BindView(R.id.tvNombre)
        TextView tvNombre;
        @BindView(R.id.tvApellido)
        TextView tvApellido;
        @BindView(R.id.tvNumeroCelular)
        TextView tvNumeroCelular;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
