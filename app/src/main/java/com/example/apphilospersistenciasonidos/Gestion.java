package com.example.apphilospersistenciasonidos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class Gestion extends Activity {
    private Partida partida;
    private int dificultad;
    private int FPS = 30;

    private Handler temporizador;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        dificultad = extras.getInt("DIFICULTAD");

        partida = new Partida(getApplicationContext(), dificultad);

        setContentView(partida);

        temporizador = new Handler();
        temporizador.postDelayed(elhilo,2000);
    }


    private Runnable elhilo = new Runnable() {
        @Override
        public void run() {

            if(partida.movimientoBola() ) fin();
            else {
                partida.invalidate(); // elimina el contenido de ImageView y llama de nuevo a onDraw

                temporizador.postDelayed(elhilo, 1000/FPS);
            }
        }
    };

    public boolean onTouchEvent(MotionEvent evento){

        int x = (int) evento.getX();
        int y = (int) evento.getY();

        partida.toque(x,y);

        return false;
    }

    public void fin(){

        temporizador.removeCallbacks(elhilo);

        finish(); // destruye actividad actual
    }
}
