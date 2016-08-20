package com.jcodee.mod1class4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jcodee.mod1class4.service.InternetService;

public class MainActivity extends AppCompatActivity {
    private Button btnIniciar, btnDetener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnDetener = (Button) findViewById(R.id.btnDetener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Iniciamos el service
                Intent intent = new Intent(MainActivity.this, InternetService.class);
                startService(intent);

            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Detenemos el service
                Intent intent = new Intent(MainActivity.this, InternetService.class);
                stopService(intent);

            }
        });
    }
}
