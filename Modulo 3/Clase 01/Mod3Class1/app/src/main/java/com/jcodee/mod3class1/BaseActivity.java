package com.jcodee.mod3class1;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Author: johannfjs
 * Date: 24/9/16
 * Time: 11:17
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        //Inicializamos todos los componentes de la vista con el ButterKnife
        ButterKnife.bind(this);
    }
}
