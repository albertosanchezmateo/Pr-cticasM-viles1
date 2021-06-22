package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {

    boolean vieneDeJugar;

    Button volverAlMenu;
    TextView textoGanador, rPrimero, rSegundo, rTercero, rCuarto, rQuinto;
    Score jugador;

    List<Score> scoresDB = new ArrayList<>();
    ScoresDB puntuaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Bundle bundle = getIntent().getExtras();

        vieneDeJugar = bundle.getBoolean("vieneDeJugar");
        volverAlMenu = findViewById(R.id.bVolverMenu);
        textoGanador = findViewById(R.id.textoGanador);
        rPrimero = findViewById(R.id.rPrimero);
        rSegundo = findViewById(R.id.rSegundo);
        rTercero = findViewById(R.id.rTercero);
        rCuarto = findViewById(R.id.rCuarto);
        rQuinto = findViewById(R.id.rQuinto);
        puntuaciones = ScoresDB.getInstance(this);

        if(vieneDeJugar){
            String nombre = bundle.getString("nombreJug");
            int aciertos = bundle.getInt("aciertos");
            int tiempo = bundle.getInt("tiempo");
            int preguntasJugadas = bundle.getInt("preguntasJugadas");
            double puntuacion = (double)aciertos / (double)tiempo;
            puntuacion = Double.parseDouble(new DecimalFormat("##.###").format(puntuacion));
            textoGanador.setText("Felicidades " + nombre + ", has acertado " + aciertos + "/" + preguntasJugadas + " en " + tiempo + " segundos, tu puntuacion de aciertos por segundo es de " + puntuacion);


            jugador = new Score(nombre, puntuacion);

            //puntuaciones.scoresDao().deleteAll();
            Score busquedaJugador = puntuaciones.scoresDao().buscarNombre(nombre);

            if(busquedaJugador != null){
                if(busquedaJugador.getPuntuacion() < jugador.getPuntuacion()){
                    puntuaciones.scoresDao().actualizar(nombre, puntuacion);
                }
            }else{
                puntuaciones.scoresDao().insert(jugador);
            }
            //puntuaciones.scoresDao().actualizar(nombre, puntuacion);

            scoresDB = puntuaciones.scoresDao().getAllScores();
            pintarRanking(scoresDB);


        }else{
            textoGanador.setVisibility(View.INVISIBLE);
            scoresDB = puntuaciones.scoresDao().getAllScores();
            if(scoresDB.size() > 0){
                pintarRanking(scoresDB);
            }else{
                rPrimero.setVisibility(View.INVISIBLE);
                rSegundo.setVisibility(View.INVISIBLE);
                rTercero.setVisibility(View.INVISIBLE);
                rCuarto.setVisibility(View.INVISIBLE);
                rQuinto.setVisibility(View.INVISIBLE);
            }

        }

        volverAlMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ranking.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void pintarRanking(List<Score> bd){
        int longitud = bd.size();
        switch (longitud){
            case 1:
                rPrimero.setText("1º: " + bd.get(0).getNombre() + "   " + bd.get(0).getPuntuacion() + " aciertos/seg");
                break;
            case 2:
                rPrimero.setText("1º: " + bd.get(0).getNombre() + "   " + bd.get(0).getPuntuacion() + " aciertos/seg");
                rSegundo.setText("2º: " + bd.get(1).getNombre() + "   " + bd.get(1).getPuntuacion() + " aciertos/seg");
                break;
            case 3:
                rPrimero.setText("1º: " + bd.get(0).getNombre() + "   " + bd.get(0).getPuntuacion() + " aciertos/seg");
                rSegundo.setText("2º: " + bd.get(1).getNombre() + "   " + bd.get(1).getPuntuacion() + " aciertos/seg");
                rTercero.setText("3º: " + bd.get(2).getNombre() + "   " + bd.get(2).getPuntuacion() + " aciertos/seg");
                break;
            case 4:
                rPrimero.setText("1º: " + bd.get(0).getNombre() + "   " + bd.get(0).getPuntuacion() + " aciertos/seg");
                rSegundo.setText("2º: " + bd.get(1).getNombre() + "   " + bd.get(1).getPuntuacion() + " aciertos/seg");
                rTercero.setText("3º: " + bd.get(2).getNombre() + "   " + bd.get(2).getPuntuacion() + " aciertos/seg");
                rCuarto.setText("4º: " + bd.get(3).getNombre() + "   " + bd.get(3).getPuntuacion() + " aciertos/seg");
                break;
            case 5:
                rPrimero.setText("1º: " + bd.get(0).getNombre() + "   " + bd.get(0).getPuntuacion() + " aciertos/seg");
                rSegundo.setText("2º: " + bd.get(1).getNombre() + "   " + bd.get(1).getPuntuacion() + " aciertos/seg");
                rTercero.setText("3º: " + bd.get(2).getNombre() + "   " + bd.get(2).getPuntuacion() + " aciertos/seg");
                rCuarto.setText("4º: " + bd.get(3).getNombre() + "   " + bd.get(3).getPuntuacion() + " aciertos/seg");
                rQuinto.setText("5º: " + bd.get(4).getNombre() + "   " + bd.get(4).getPuntuacion() + " aciertos/seg");
                break;

        }
    }
}