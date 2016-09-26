package com.jcodee.mod2class4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.sdvImagen)
    SimpleDraweeView sdvImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        sdvImagen.startAnimation(animation);

        //Creamos un hilo
        new Handler()
                .postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                //Se ejecuta después que finaliza el tiempo
                                Intent intent =
                                        new Intent(SplashActivity.this, RegistroActivity.class);
                                startActivity(intent);
                                //overridePendingTransition();
                            }
                        },
                        5000);//Tiempo en milisegundos
    }
}
