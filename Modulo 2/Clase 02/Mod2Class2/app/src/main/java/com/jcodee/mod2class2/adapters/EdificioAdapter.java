package com.jcodee.mod2class2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod2class2.R;
import com.jcodee.mod2class2.models.EdificioModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: johannfjs
 * Date: 3/9/16
 * Time: 14:30
 */
public class EdificioAdapter extends RecyclerView.Adapter<EdificioAdapter.ViewHolder> {
    private ArrayList<EdificioModel> lista;

    public EdificioAdapter(ArrayList<EdificioModel> lista) {
        this.lista = lista;
    }

    //Creamos las variables de los componentes
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lblNombre)
        TextView lblNombre;
        @BindView(R.id.lblDireccion)
        TextView lblDireccion;
        @BindView(R.id.lblAmbiente)
        TextView lblAmbientes;
        @BindView(R.id.lblPrecio)
        TextView lblPrecio;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Anexamos el diseño a nuestro adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }

    //Seteamos los datos de cada item por los que obtenemos desde la lista
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EdificioModel edificioModel = lista.get(position);
        holder.lblNombre.setText(edificioModel.getNombre());
        holder.lblDireccion.setText(edificioModel.getDireccion());
        holder.lblAmbientes.setText(String.valueOf(edificioModel.getAmbientes()));
        holder.lblPrecio.setText(String.valueOf(edificioModel.getPrecio()));
    }

    //Cantidad de elementos que hay en la lista
    @Override
    public int getItemCount() {
        return lista.size();
    }
}
