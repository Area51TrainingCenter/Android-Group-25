package com.jcodee.mod2class4.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.entities.Llamada;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: johannfjs
 * Date: 17/9/16
 * Time: 13:02
 */
public class LlamadaAdapter extends RecyclerView.Adapter<LlamadaAdapter.MyViewHolder> {
    private ArrayList<Llamada> lista;

    public LlamadaAdapter(ArrayList<Llamada> lista) {
        this.lista = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_llamada, parent, false);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Llamada llamada = lista.get(position);
        holder.tvNumeroCelular.setText(llamada.getNumeroCelular());
        holder.tvFechaHora.setText(llamada.getFechaHora());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNumeroCelular)
        TextView tvNumeroCelular;
        @BindView(R.id.tvFechaHora)
        TextView tvFechaHora;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
