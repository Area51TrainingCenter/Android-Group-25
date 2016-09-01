package com.jcodee.mod2class1.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod2class1.R;
import com.jcodee.mod2class1.modelos.Datos;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: johannfjs
 * Date: 27/8/16
 * Time: 13:50
 */
public class DatoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Datos> lista;

    public DatoAdapter(Context context, ArrayList<Datos> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        @BindView(R.id.tvNombre)
        TextView tvNombre;
        @BindView(R.id.tvCorreo)
        TextView tvCorreo;
        @BindView(R.id.sdvImagen)
        SimpleDraweeView sdvImagen;
        @BindView(R.id.llContenedor)
        LinearLayout llContenedor;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Datos datos = (Datos) getItem(position);
        viewHolder.tvNombre.setText(datos.getNombre());

        viewHolder.tvCorreo.setText(datos.getCorreo());
        switch (datos.getIdGenero()) {
            case 1:
                viewHolder.sdvImagen.setImageURI(Uri.parse("res:/" + R.drawable.ic_man));
                viewHolder.llContenedor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));
                break;
            case 2:
                viewHolder.sdvImagen.setImageURI(Uri.parse("res:/" + R.drawable.ic_female));
                viewHolder.llContenedor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
                break;
        }

        return convertView;
    }
}
