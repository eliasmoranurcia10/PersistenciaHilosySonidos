package com.example.apphilospersistenciasonidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //llamar al archivo AyudaAcividad
    public void ayuda(View vista){
        //Instanciar Intent para llamar otro layout, llamar a la clase AyudaActividad
        Intent intencion = new Intent(this,AyudaActividad.class);
        //Empezar la actividad
        startActivity(intencion);
    }
}