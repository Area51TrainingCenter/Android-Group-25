package com.jcodee.mod1class3.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod1class3.R;
import com.jcodee.mod1class3.modelos.Producto;

import java.util.ArrayList;

/**
 * Author: johannfjs
 * Date: 6/8/16
 * Time: 11:03
 */
public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> lista;

    public ProductoAdapter(Context context, ArrayList<Producto> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Creamos clase para tener los datos de la vista
    static class ViewHolder {
        //Variables
        TextView tvNombre, tvProducto, tvPrecio;
        SimpleDraweeView sdvImagen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Validamos que el diseño ya este cargado
        if (convertView == null) {
            //Si no está cargado, nosotros lo cargamos
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            //Inicializamos todas las variables
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvProducto = (TextView) convertView.findViewById(R.id.tvProducto);
            viewHolder.tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
            viewHolder.tvPrecio = (TextView) convertView.findViewById(R.id.tvPrecio);
            viewHolder.sdvImagen = (SimpleDraweeView) convertView.findViewById(R.id.sdvImagen);
            //Guardamos la inicialización para poderla reutilizar
            //El TAG es un comodin que acepta tod o tipo de variables
            convertView.setTag(viewHolder);
        }
        //Obtenemos las referencias almacenadas en nuestro diseño
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Producto producto = (Producto) getItem(position);

        //Cargamos la data a nuestro diseño
        viewHolder.tvNombre.setText(producto.getNombre());
        viewHolder.tvProducto.setText(producto.getProducto());
        viewHolder.tvPrecio.setText(String.valueOf(producto.getPrecio()));
        viewHolder.sdvImagen.setImageURI(Uri.parse("res:/" + R.drawable.ic_launcher));
        //viewHolder.sdvImagen.setImageURI(Uri.parse("https://userscontent2.emaze.com/images/12ce5466-8df0-438e-8f38-d8beac82f6aa/508e56c2-b53f-40b1-a2ba-0531a0fd82df.jpg"));

        //Retornamos el diseño con los cambios realizados
        return convertView;
    }
}
