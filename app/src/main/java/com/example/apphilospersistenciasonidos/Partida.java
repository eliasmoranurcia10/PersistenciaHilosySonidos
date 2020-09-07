package com.example.apphilospersistenciasonidos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import androidx.core.content.ContextCompat;

/**
 * Created by Juan on 23/02/2017.
 */

public class Partida extends AppCompatImageView {

    private int acel;
    private Bitmap pelota, fondo;
    private int tam_pantX, tam_pantY, posX, posY, velX, velY;
    private int tamPelota;
    boolean pelota_sube;

    public Partida(Context contexto, int nivel_dificultad){ //Recibir dos parámetros context y nivel_dificultad

        super(contexto); //Llamando a la clase padre

        WindowManager manejador_ventana=(WindowManager) contexto.getSystemService(Context.WINDOW_SERVICE); //Interface, comuniarse con el manejador de ventanas
        //Que tipo de pantalla utilizamos

        Display pantalla=manejador_ventana.getDefaultDisplay(); //

        Point maneja_coord=new Point(); //integra dos coordenadas x e y

        pantalla.getSize(maneja_coord); //Obtener el tamaño de la pantalla donde se está ejeutando la aplicación

        tam_pantX=maneja_coord.x;

        tam_pantY=maneja_coord.y;

        //Construir un layout programático, construir el fondo de la partida
        BitmapDrawable dibujo_fondo=(BitmapDrawable) ContextCompat.getDrawable(contexto, R.drawable.paisaje_1);

        fondo=dibujo_fondo.getBitmap();// mirar en api getBitmap en clase BitmapDrawable. esto nos lleva a la siguiente instr.

        fondo=Bitmap.createScaledBitmap(fondo, tam_pantX, tam_pantY, false);//mirar en clase Bitmap

        //dibujar la pelota
        BitmapDrawable objetoPelota=(BitmapDrawable)ContextCompat.getDrawable(contexto, R.drawable.pelota_1);

        pelota=objetoPelota.getBitmap();

        tamPelota=tam_pantY/3; //El tamaño de la pelota es un tercio de la pantalla

        pelota=Bitmap.createScaledBitmap(pelota, tamPelota, tamPelota, false); //asignar la pelota, ssu ancho y su largo sin filtro

        //mostrar a la pelota en la pantalla

        posX=tam_pantX/2-tamPelota/2; // poner la pelota en medio de la pantalla

        posY=0-tamPelota; //poner a la pelota arriba en la pantalla

        acel=nivel_dificultad*(maneja_coord.y/400); // Aceleración que tendrá la pelota según el nivel de dificultad


    }

    public boolean toque(int x, int y){

        if(y<tam_pantY/3) return false;

        if(velY<=0) return false;

        if(x<posX || x> posX+tamPelota) return false;

        if(y<posY || y>posY+tamPelota) return false;

        velY=-velY;

        double desplX=x-(posX+tamPelota/2);

        desplX=desplX/(tamPelota/2)*velY/2;

        velX+=(int)desplX;

        return true;
    }


    public boolean movimientoBola(){

        if(posX<0-tamPelota){

            posY=0-tamPelota;

            velY=acel;
        }

        posX+=velX;

        posY+=velY;

        if(posY>=tam_pantY) return true;

        if(posX+tamPelota<0 || posX>tam_pantX) return true;

        if(velY<0) pelota_sube=true;

        if(velY>0 && pelota_sube){

            pelota_sube=false;

        }

        velY+=acel;

        return false;
    }

    protected void onDraw(Canvas lienzo){

        lienzo.drawBitmap(fondo, 0,0, null);

        lienzo.drawBitmap(pelota, posX, posY, null);


    }
}
