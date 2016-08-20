package com.jcodee.mod1class4_2.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod1class4_2.R;
import com.jcodee.mod1class4_2.modelos.Foto;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 12:11
 */
public class FotoAdapter extends ArrayAdapter<Foto> {
    private Context context;
    private ArrayList<Foto> lista;

    public FotoAdapter(Context context, ArrayList<Foto> objects) {
        super(context, 0, objects);
        this.context = context;
        this.lista = objects;
    }

    static class ViewHolder {
        SimpleDraweeView sdvImagen;
        TextView tvTitulo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declaramos una variable de tipo ViewHolder
        ViewHolder viewHolder;
        //Verificamos que la parte visual este cargada
        if (convertView == null) {
            //Ponemos la parte visual
            convertView = LayoutInflater.from(context).inflate(R.layout.item_foto, parent, false);
            //Inicializamos el viewHolder
            viewHolder = new ViewHolder();
            //Vinculamos las variables con sus componentes
            viewHolder.sdvImagen = (SimpleDraweeView) convertView.findViewById(R.id.sdvImagen);
            viewHolder.tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
            //Cargamos las variables a la parte visual
            convertView.setTag(viewHolder);
        }
        //Obtenemos la inicialización realizada
        viewHolder = (ViewHolder) convertView.getTag();
        //Obtenemos los datos de la lista según la posición
        Foto item = lista.get(position);
        //Setteamos los valores
        viewHolder.sdvImagen.setImageURI(Uri.parse(item.getRutaFoto()));
        viewHolder.tvTitulo.setText(item.getTitulo());

        return convertView;
    }
}
