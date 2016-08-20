package com.jcodee.mod1class4_2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcodee.mod1class4_2.adapters.ViewPagerAdapter;

public class Detalle2Activity extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle2);

        //inicializamos las variables
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Inicializamos el adapter
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        if (getIntent().hasExtra("posicion")) {
            int posicion = getIntent().getIntExtra("posicion", -1);
            viewPager.setCurrentItem(posicion);
        }
    }
}
