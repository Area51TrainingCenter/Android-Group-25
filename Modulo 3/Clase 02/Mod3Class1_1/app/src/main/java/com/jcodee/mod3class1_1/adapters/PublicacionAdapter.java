package com.jcodee.mod3class1_1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod3class1_1.R;
import com.jcodee.mod3class1_1.models.Publicacion;

import java.util.ArrayList;

/**
 * Author: johannfjs
 * Date: 1/10/16
 * Time: 10:26
 */

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.MyViewHolder> {
    private ArrayList<Publicacion> lista;

    public PublicacionAdapter(ArrayList<Publicacion> lista) {
        this.lista = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_publicacion, parent, false);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Publicacion item = lista.get(position);

        holder.tvTitulo.setText(item.getTitulo());
        holder.tvCuerpo.setText(item.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvCuerpo;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvCuerpo = (TextView) itemView.findViewById(R.id.tvCuerpo);
        }
    }
}
