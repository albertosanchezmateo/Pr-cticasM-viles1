package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements FragPreguntasTexto.FragPTextoListener, FragPreguntaVideo.FragPVideoListener, FragPreguntaAudio.FragPAudioListener {

    TextView nomJug, currentPreguntas, acertadasPreguntas;
    Button botComenzar;
    Chronometer cronometro;

    List<Pregunta> listaPreguntas = new ArrayList<>();
    PreguntasDB baseDeDatos;



    FragPreguntasTexto fpt;
    FragPreguntaVideo fpv;
    FragPreguntaAudio fpa;

    int fragmentoActual = -1;
    String nombreJugador;
    int numeroDePreguntas;
    int preguntasJugadas = 1;
    int aciertos = 0;
    Random rand = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();

        nombreJugador = bundle.getString("nombreDelJugador");
        numeroDePreguntas = bundle.getInt("cantidadPreguntas");

        baseDeDatos = PreguntasDB.getInstance(this);
        listaPreguntas = baseDeDatos.preguntaDao().getAll();
        if(listaPreguntas.size() < 1){
            InicializarBaseDeDatos();
            listaPreguntas = baseDeDatos.preguntaDao().getAll();
        }

        fpt = new FragPreguntasTexto();
        fpv = new FragPreguntaVideo();
        fpa = new FragPreguntaAudio();

        nomJug = (TextView) findViewById(R.id.nomJug);
        currentPreguntas = (TextView) findViewById(R.id.currentPreguntas);
        acertadasPreguntas = (TextView) findViewById(R.id.acertadasPreguntas);
        cronometro = (Chronometer) findViewById(R.id.crono);

        cronometro.setFormat("Tiempo: %s");

        nomJug.setText(nombreJugador);
        currentPreguntas.setText("Pregunta: " + preguntasJugadas + "/" + numeroDePreguntas);
        acertadasPreguntas.setText("Aciertos: " + aciertos);

        botComenzar = (Button) findViewById(R.id.botEjemplo);
        botComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botComenzar.setVisibility(v.GONE);
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.start();
                nuevaPregunta();
            }
        });
        //nuevaPregunta();

    }

    public void InicializarBaseDeDatos(){
        String path1 = "android.resource://" + getPackageName() + "/" + R.raw.fiddle;
        String path2 = "android.resource://" + getPackageName() + "/" + R.raw.heimer;
        String path3 = "android.resource://" + getPackageName() + "/" + R.raw.jinx;
        String path4 = "android.resource://" + getPackageName() + "/" + R.raw.garen;
        String path5 = "android.resource://" + getPackageName() + "/" + R.raw.jarvan;
        String path6 = "android.resource://" + getPackageName() + "/" + R.raw.lulu;
        String path7 = "android.resource://" + getPackageName() + "/" + R.raw.trundle;
        String path8 = "android.resource://" + getPackageName() + "/" + R.raw.thresh;
        String path9 = "android.resource://" + getPackageName() + "/" + R.raw.reksai;
        String path10 = "android.resource://" + getPackageName() + "/" + R.raw.oriana;
        String path11 = "android.resource://" + getPackageName() + "/" + R.raw.fakerzed;
        String path12 = "android.resource://" + getPackageName() + "/" + R.raw.backdoor;
        String path13 = "android.resource://" + getPackageName() + "/" + R.raw.misfits;
        String path14 = "android.resource://" + getPackageName() + "/" + R.raw.ig;
        String path15 = "android.resource://" + getPackageName() + "/" + R.raw.zoe;

        Pregunta p1 = new Pregunta(0, "¿Cuál de estas siglas no corresponde a una liga?", "LCS", "LCK", "LEL", "LPL", "LEL");
        Pregunta p2 = new Pregunta(0, "¿Qué jugador no ha jugado nunca en Fnatic?", "Soaz", "Xpekee", "Bwipo", "Ocelote", "Ocelote");
        Pregunta p3 = new Pregunta(0, "¿Cuántos torneos Worlds han habido en la historia?", "10", "15", "5", "0", "10");
        Pregunta p4 = new Pregunta(0, "¿Cómo se llama la liga española del lol?", "SuperLiga", "LEL", "ProLeague", "SPO", "SuperLiga");
        Pregunta p5 = new Pregunta(0, "¿Cuántos roles hay en un equipo?", "6", "7", "8", "5", "5");
        Pregunta p6 = new Pregunta(0, "¿Cuál de estos equipos es norte americano?", "Cloud9", "Rogue", "SKT", "Sunning", "Cloud9");
        Pregunta p7 = new Pregunta(0, "¿Cuántos personajes había en el lanzamiento del juego?", "15", "27", "36", "58", "36");
        Pregunta p8 = new Pregunta(0, "¿Cuántos equipos hay en la liga europea del lol?", "8", "10", "12", "16", "10");
        Pregunta p9 = new Pregunta(0, "¿Cuál de estos equipos no tiene un origen español?", "Origen", "Giants", "MadLions", "Fnatic", "Fnatic");
        Pregunta p10 = new Pregunta(0, "¿Qué rol no tiene calle asignada?", "Top", "Support", "Mid", "Jungla", "Jungla");
        Pregunta p11 = new Pregunta(2, path1, "Tryndramere", "Fiddlesticks", "Nocturne", "Warwick", "Fiddlesticks");
        Pregunta p12 = new Pregunta(2, path2, "Heimerdinger", "Ziggs", "Rumble", "Jayce", "Heimerdinger");
        Pregunta p13 = new Pregunta(2, path3, "Vi", "Caitlyn",  "Jinx", "Camille", "Jinx");
        Pregunta p14 = new Pregunta(2, path4, "Garen", "Jarvan", "Draven", "Darius", "Garen");
        Pregunta p15 = new Pregunta(2, path5, "Xin Zhao", "Sett", "Garen", "Jarvan", "Jarvan");
        Pregunta p16 = new Pregunta(2, path6, "Lulu", "Tristana", "Jinx", "Nami", "Lulu");
        Pregunta p17 = new Pregunta(2, path7, "Trundle", "Braum", "Udyr", "Viktor", "Trundle");
        Pregunta p18 = new Pregunta(2, path8, "Nocturne", "Fiddlesticks", "Thresh", "Hecarim", "Thresh");
        Pregunta p19 = new Pregunta(2, path9, "Kai Sa", "Kog Maw", "Rek Sai", "Kha Zix", "Rek Sai");
        Pregunta p20 = new Pregunta(2, path10, "Oriana ", "Camille", "Vi", "Caitlyn", "Oriana");
        Pregunta p21 = new Pregunta(1, path11, "Faker muere","Ryu muere","Ambos mueren","Ambos sobreviven","Ryu muere");
        Pregunta p22 = new Pregunta(1, path12, "Xpeke gana","Xpeke muere","Xpeke muere pero consigue ganar","Gana el equipo rival","Xpeke gana");
        Pregunta p23 = new Pregunta(1, path13, "Mueren los dos adcs","Mueren los dos supps","Muere SKT","Muere Misfits","Muere SKT");
        Pregunta p24 = new Pregunta(1, path14, "Lwx sobrevive","Lwx gana la partida","IG gana","Ninguno consigue terminar","IG gana");
        Pregunta p25 = new Pregunta(1, path15, "Xayah muere","Ninguno muere","Ambos mueren","Zoe muere","Zoe muere");

        baseDeDatos.preguntaDao().insert(p1);
        baseDeDatos.preguntaDao().insert(p2);
        baseDeDatos.preguntaDao().insert(p3);
        baseDeDatos.preguntaDao().insert(p4);
        baseDeDatos.preguntaDao().insert(p5);
        baseDeDatos.preguntaDao().insert(p6);
        baseDeDatos.preguntaDao().insert(p7);
        baseDeDatos.preguntaDao().insert(p8);
        baseDeDatos.preguntaDao().insert(p9);
        baseDeDatos.preguntaDao().insert(p10);
        baseDeDatos.preguntaDao().insert(p11);
        baseDeDatos.preguntaDao().insert(p12);
        baseDeDatos.preguntaDao().insert(p13);
        baseDeDatos.preguntaDao().insert(p14);
        baseDeDatos.preguntaDao().insert(p15);
        baseDeDatos.preguntaDao().insert(p16);
        baseDeDatos.preguntaDao().insert(p17);
        baseDeDatos.preguntaDao().insert(p18);
        baseDeDatos.preguntaDao().insert(p19);
        baseDeDatos.preguntaDao().insert(p20);
        baseDeDatos.preguntaDao().insert(p21);
        baseDeDatos.preguntaDao().insert(p22);
        baseDeDatos.preguntaDao().insert(p23);
        baseDeDatos.preguntaDao().insert(p24);
        baseDeDatos.preguntaDao().insert(p25);
    }

    public void nuevaPregunta(){

        int idPregunta = rand.nextInt(listaPreguntas.size());

        int codigoFragmento; //0 para texo, 1 para video, 2 para audio
        String enunciado, r1 = "", r2 = "", r3 = "", r4 = "", rC, respuestaCorrecta = "";

        codigoFragmento = listaPreguntas.get(idPregunta).getTipoPregunta();
        enunciado = listaPreguntas.get(idPregunta).getEnunciado();

        r1 = listaPreguntas.get(idPregunta).getRespuesta1();
        r2 = listaPreguntas.get(idPregunta).getRespuesta2();
        r3 = listaPreguntas.get(idPregunta).getRespuesta3();
        r4 = listaPreguntas.get(idPregunta).getRespuesta4();


        rC = listaPreguntas.get(idPregunta).getRespuestaCorr();

        listaPreguntas.remove(idPregunta);

        if(fragmentoActual == -1){
            switch(codigoFragmento){
                case 0:
                    transicionarTexto(enunciado, r1, r2, r3, r4, rC);
                    break;
                case 1:
                    transicionarVideo(enunciado, r1, r2, r3, r4, rC);
                    break;
                case 2:
                    transicionarAudio(enunciado, r1, r2, r3, r4, rC);
                    break;
            }
        }else if(fragmentoActual == 0){
            switch(codigoFragmento){
                case 0:
                    fpt.nuevaPreguntaTexto(enunciado, r1, r2, r3, r4, rC);
                    fragmentoActual = codigoFragmento;
                    break;
                case 1:
                    transicionarVideo(enunciado, r1, r2, r3, r4, rC);
                    break;
                case 2:
                    transicionarAudio(enunciado, r1, r2, r3, r4, rC);
                    break;
            }
        }else if(fragmentoActual == 1){
            switch(codigoFragmento){
                case 0:
                    transicionarTexto(enunciado, r1, r2, r3, r4, rC);
                    break;
                case 1:
                    fpv.nuevaPreguntaVideo(enunciado, r1, r2, r3, r4, rC);
                    fragmentoActual = codigoFragmento;
                    break;
                case 2:
                    transicionarAudio(enunciado, r1, r2, r3, r4, rC);
                    break;
            }
        }else if(fragmentoActual == 2){
            fpa.mediaplayer.stop();
            switch(codigoFragmento){
                case 0:
                    transicionarTexto(enunciado, r1, r2, r3, r4, rC);
                    break;
                case 1:
                    transicionarVideo(enunciado, r1, r2, r3, r4, rC);
                    break;
                case 2:
                    fpa.nuevaPreguntaAudio(enunciado, r1, r2, r3, r4, rC);
                    fragmentoActual = codigoFragmento;
                    break;
            }
        }
    }

    public void transicionarTexto(String enunciado, String r1, String r2,String r3,String r4,String rC){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaccion = fragmentManager.beginTransaction();
        transaccion.replace(R.id.layu, fpt);
        transaccion.commitNow();
        fpt.nuevaPreguntaTexto(enunciado, r1, r2, r3, r4, rC);
        fragmentoActual = 0;
    }

    public void transicionarVideo(String enunciado, String r1, String r2,String r3,String r4,String rC){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaccion = fragmentManager.beginTransaction();
        transaccion.replace(R.id.layu, fpv);
        transaccion.commitNow();
        fpv.nuevaPreguntaVideo(enunciado, r1, r2, r3, r4, rC);
        fragmentoActual = 1;
    }

    public void transicionarAudio(String enunciado, String r1, String r2,String r3,String r4,String rC){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaccion = fragmentManager.beginTransaction();
        transaccion.replace(R.id.layu, fpa);
        transaccion.commitNow();
        fpa.nuevaPreguntaAudio(enunciado, r1, r2, r3, r4, rC);
        fragmentoActual = 2;
    }


    public void terminarPartida(){
        if(fragmentoActual == 2){
            fpa.mediaplayer.stop();
        }
        int tiempoFinal = (int) (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
        cronometro.stop();
        Intent intent = new Intent(GameActivity.this, Ranking.class);
        intent.putExtra("nombreJug", nombreJugador);
        intent.putExtra("aciertos", aciertos);
        intent.putExtra("preguntasJugadas", numeroDePreguntas);
        intent.putExtra("vieneDeJugar", true);
        intent.putExtra("tiempo", tiempoFinal);
        startActivity(intent);
    }

    @Override
    public void onRespuestaTexto(boolean correcto) {
        preguntasJugadas++;
        if(correcto) {
            aciertos++;
            acertadasPreguntas.setText("Aciertos: " + aciertos);
        }
        if(preguntasJugadas > numeroDePreguntas){
            terminarPartida();
        }else{
            currentPreguntas.setText("Pregunta: " + preguntasJugadas + "/" + numeroDePreguntas);
            nuevaPregunta();
        }

    }

    @Override
    public void onRespuestaVideo(boolean correcto) {
        preguntasJugadas++;
        if(correcto) {
            aciertos++;
            acertadasPreguntas.setText("Aciertos: " + aciertos);
        }
        if(preguntasJugadas > numeroDePreguntas){
            terminarPartida();
        }else{
            currentPreguntas.setText("Pregunta: " + preguntasJugadas + "/" + numeroDePreguntas);
            nuevaPregunta();
        }
        //String path = "android.resource://" + getPackageName() + "/" + R.raw.demostracion;

    }

    @Override
    public void onRespuestaAudio(boolean correcto) {
        preguntasJugadas++;
        if(correcto) {
            aciertos++;
            acertadasPreguntas.setText("Aciertos: " + aciertos);
        }
        if(preguntasJugadas > numeroDePreguntas){
            terminarPartida();
        }else{
            currentPreguntas.setText("Pregunta: " + preguntasJugadas + "/" + numeroDePreguntas);
            nuevaPregunta();
        }
    }



}