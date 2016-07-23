package com.jcodee.mod1class2_2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jcodee.mod1class2_2.R;
import com.jcodee.mod1class2_2.modelos.Producto;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Author: johannfjs
 * Date: 23/7/16
 * Time: 13:00
 */
public class ProductoAdapter extends BaseAdapter {
    //Para saber en que página nos encontramos y se retornara el cambio
    private Context context;
    //La lista de objetos a iterar
    private ArrayList<Producto> lista;

    //Constructor para recibir los datos del activity
    public ProductoAdapter(Context context, ArrayList<Producto> lista) {
        this.context = context;
        this.lista = lista;
    }

    //Para la cantidad de elementos que se tiene, según esto se va a iterar
    @Override
    public int getCount() {
        return lista.size();
    }

    //Para obtener el objeto de la lista según la posición
    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    //Para obtener el id del objeto de la lista según la posición
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Es para iterar y crear el elemento a mostrar en el item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Verificamos que la parte visual no esté cargada
        if (convertView == null) {
            //Si es no está cargada, la inicializamos
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }
        //declaración de variables e inicialización
        TextView tvProducto = (TextView) convertView.findViewById(R.id.tvProducto);
        TextView tvCantidad = (TextView) convertView.findViewById(R.id.tvCantidad);
        TextView tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
        TextView tvPrecio = (TextView) convertView.findViewById(R.id.tvPrecio);

        //Obtenemos los datos del objeto de la lista
        Producto producto = (Producto) getItem(position);
        //Setteamos los datos de los componentes
        tvProducto.setText(producto.getNombre());
        tvCantidad.setText(String.valueOf(producto.getCantidad()));
        tvPrecio.setText(String.valueOf(producto.getPrecio()));
        tvTotal.setText(String.valueOf(producto.getCantidad() * producto.getPrecio()));

        return convertView;
    }
}
