package com.jcodee.mod2class4.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Author: johannfjs
 * Date: 17/9/16
 * Time: 10:17
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> listFragment = new ArrayList<>();
    private ArrayList<String> listTituloFragment = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Creado un metodo para que podamos agregar fragmentos (pantallas)
    // con sus respectivos titulos
    public void agregarFragmento(Fragment fragment, String titulo) {
        listFragment.add(fragment);
        listTituloFragment.add(titulo);
    }

    @Override
    public Fragment getItem(int position) {
        //Retornamos el fragmento seleccionado
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        //Cantidad de pantallas que habrá
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Retornando el titulo del Tab
        return listTituloFragment.get(position);
    }
}