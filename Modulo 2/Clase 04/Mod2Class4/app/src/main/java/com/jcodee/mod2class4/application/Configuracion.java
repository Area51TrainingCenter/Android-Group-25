package com.jcodee.mod2class4.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Author: johannfjs
 * Date: 17/9/16
 * Time: 12:38
 */
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder(getApplicationContext())
                        .name("mod2class4.realm")
                        .schemaVersion(1)
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Fresco.initialize(getApplicationContext());
    }
}
