package com.jcodee.mod1class4_2.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jcodee.mod1class4_2.MainActivity;
import com.jcodee.mod1class4_2.fragment.FotoFragment;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 15:09
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Creamos la pantalla a mostrar según la posición
        FotoFragment fotoFragment = new FotoFragment();
        //Creamos un bundle para pasar la información entre los fragmentos
        Bundle bundle = new Bundle();
        //Enviamos la posición
        bundle.putInt("posicion", position);
        //Agregamos los datos a enviar al fragmento
        fotoFragment.setArguments(bundle);
        //Retornamos el fragment con los datos
        return fotoFragment;
    }

    @Override
    public int getCount() {
        return MainActivity.listaFotos.size();
    }
}
