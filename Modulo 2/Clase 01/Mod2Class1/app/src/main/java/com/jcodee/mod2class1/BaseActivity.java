package com.jcodee.mod2class1;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jcodee.mod2class1.sqlite.Querys;

import butterknife.ButterKnife;

/**
 * Author: johannfjs
 * Date: 27/8/16
 * Time: 10:43
 */
public class BaseActivity extends AppCompatActivity {
    public Querys querys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        querys = new Querys(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        //Inicializamos la librería del butterknife a está página
        ButterKnife.bind(this);
    }
}
