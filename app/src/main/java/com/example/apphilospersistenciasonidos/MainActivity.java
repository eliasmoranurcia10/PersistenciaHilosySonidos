package com.example.apphilospersistenciasonidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void dificultad(View vista){

        String dific = (String) ((Button) vista).getText();

        int dificultad = 1;

        if(dific.equals("Normal")) dificultad= 2;
        if(dific.equals("Difícil")) dificultad= 3;

        Intent in = new Intent(this,Gestion.class);

        in.putExtra("DIFICULTAD", dificultad);

        startActivity(in);

    }
}