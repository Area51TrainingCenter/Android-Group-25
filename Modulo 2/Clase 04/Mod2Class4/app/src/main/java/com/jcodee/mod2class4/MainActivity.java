package com.jcodee.mod2class4;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.jcodee.mod2class4.adapter.ViewPagerAdapter;
import com.jcodee.mod2class4.fragments.ContactoFragment;
import com.jcodee.mod2class4.fragments.LlamadaFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos el adapter que manejara el ViewPager
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Agregamos las pantallas a mostrar
        adapter.agregarFragmento(new LlamadaFragment(), "Llamadas");
        adapter.agregarFragmento(new ContactoFragment(), "Contactos");
        //Seteamos el adapter del ViewPager
        viewPager.setAdapter(adapter);

        //Reflejamos las pantallas agregadas al ViewPager en nuestro TabLayout
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(R.mipmap.ic_launcher);
    }
}
