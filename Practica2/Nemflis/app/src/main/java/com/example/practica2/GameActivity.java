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

public class GameActivity extends AppCompatActivity implements FragPreguntasTexto.FragPTextoListener, FragPreguntaVideo.FragPVideoListener, FragPreguntaAudio.FragPAudioListener, FragPreguntaImagen.FragPImagenListener {

    TextView nomJug, currentPreguntas, acertadasPreguntas;
    Button botComenzar;
    Chronometer cronometro;

    List<Pregunta> listaPreguntas = new ArrayList<>();
    PreguntasDB baseDeDatos;



    FragPreguntasTexto fpt;
    FragPreguntaVideo fpv;
    FragPreguntaAudio fpa;
    FragPreguntaImagen fpi;

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
        String path1 = "android.resource://" + getPackageName() + "/" + R.raw.baile;
        String path2 = "android.resource://" + getPackageName() + "/" + R.raw.lagrimas;
        String path3 = "android.resource://" + getPackageName() + "/" + R.raw.gladiator;
        String path4 = "android.resource://" + getPackageName() + "/" + R.raw.rabbit;
        String path5 = "android.resource://" + getPackageName() + "/" + R.raw.babe;
        String path6 = "android.resource://" + getPackageName() + "/" + R.raw.vengadores;
        String path7 = "android.resource://" + getPackageName() + "/" + R.raw.brian;
        String path8 = "android.resource://" + getPackageName() + "/" + R.raw.potter;
        String path9 = "android.resource://" + getPackageName() + "/" + R.raw.piratas;
        String path10 = "android.resource://" + getPackageName() + "/" + R.raw.cazafantasmas;
        int path11 = R.drawable.morgana;
        /*Pregunta p1 = new Pregunta(0, "¿A que se dedica Salvatore cuando es adulto en la pelicula Cinema Paradiso?", "Reportero", "Director de cine", "Arquitecto", "Fotógrafo", "Director de cine");
        Pregunta p2 = new Pregunta(0, "¿Quién interpreta a Jimmy en la pelicula Pulp Fiction?", "Samuel L Jackson", "John Travolta", "Quentin Tarantino", "Bruce Willis", "Quentin Tarantino");
        Pregunta p3 = new Pregunta(0, "¿Qué pelicula de ciencia ficcion postapocaliptica ha protagonizado Tom Hardy?", "Mad Max", "Venom", "El caballero oscuro", "Seven", "Mad Max");
        Pregunta p4 = new Pregunta(0, "¿Con que pelicula ganó un Óscar Leonardo Di Caprio?", "Titanic", "Django: Desencadenado", "El Renacido", "El lobo de Wall street", "El Renacido");
        Pregunta p5 = new Pregunta(0, "¿Cuantas peliculas de spiderman hay en el MCU (Marvel Cinematic Universe)?", "2", "3", "7", "5", "2");
        Pregunta p6 = new Pregunta(0, "¿A quién da vida Tom Holland?", "Spiderman", "Batman", "AntMan", "Joker", "Spiderman");
        Pregunta p7 = new Pregunta(0, "¿Qué película no pertenece a la trilogía?", "El retorno del Rey", "La Comunidad del Anillo", "La desolación de Smaug", "Las dos torres", "La desolación de Smaug");
        Pregunta p8 = new Pregunta(0, "¿En qué película sale el robot modelo T-800?", "Yo, Robot", "Cortocircuito", "Terminator", "Robocop", "Terminator");
        Pregunta p9 = new Pregunta(0, "¿Quién debía estar en casa antes de las 12?", "Blancanieves", "Ariel", "La Cenicienta", "Rapunzel", "La Cenicienta");
        Pregunta p10 = new Pregunta(0, "¿Quién dirigió la trilogía original de Star Wars?", "George Lucas", "Garet Edwards", "Rian Johnson", "JJ Abrams", "George Lucas");
        Pregunta p11 = new Pregunta(1, path2, "Blade runner", "Star wars", "Star Trek", "Blade Runner 2049", "Blade runner");
        Pregunta p12 = new Pregunta(1, path1, "Dirty dancing", "Grease", "Pulp Fiction", "Kill Bill", "Pulp Fiction");
        Pregunta p13 = new Pregunta(1, path5, "Babe, el cerdito pastor", "Babe, el cerdito valiente",  "Gordy", "Rebelión en la granja", "Babe, el cerdito valiente");
        Pregunta p14 = new Pregunta(1, path4, "Quién engañó a Roger rabbit", "Peter Rabbit", "Bugs Bunny", "El conejo loco", "Quién engañó a Roger rabbit");
        Pregunta p15 = new Pregunta(1, path3, "Centurión", "La última legión", "Ágora", "Gladiator", "Gladiator");
        Pregunta p16 = new Pregunta(2, path6, "Spiderman", "Iron Man", "Los Vengadores", "Superman", "Los Vengadores");
        Pregunta p17 = new Pregunta(2, path7, "La vida de Brian", "Shrek", "Los caballeros de la mesa cuadrada", "El sentido de la vida", "La vida de Brian");
        Pregunta p18 = new Pregunta(2, path8, "Matilda", "Narnia", "Harry Potter", "Polar Express", "Harry Potter");
        Pregunta p19 = new Pregunta(2, path9, "Indiana Jones", "Piratas del Caribe", "Los Vengadores", "Fast & Furious", "Piratas del Caribe");
        Pregunta p20 = new Pregunta(2, path10, "GhostBusters ", "Atrapado en el tiempo", "Gordy", "Misión Imposible", "GhostBusters");*/
        Pregunta p21 = new Pregunta("asfdagagadgadgfasfas", path11, path11, path11,path11, "1", 3);

        /*baseDeDatos.preguntaDao().insert(p1);
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
        baseDeDatos.preguntaDao().insert(p20);*/
        baseDeDatos.preguntaDao().insert(p21);
    }

    public void nuevaPregunta(){

        int idPregunta = rand.nextInt(listaPreguntas.size());

        int codigoFragmento; //0 para texo, 1 para video, 2 para audio
        String enunciado, r1 = "", r2 = "", r3 = "", r4 = "", rC, respuestaCorrecta = "";
        int ruta1 = 0,ruta2 = 0,ruta3 = 0,ruta4 = 0;
        codigoFragmento = listaPreguntas.get(idPregunta).getTipoPregunta();
        enunciado = listaPreguntas.get(idPregunta).getEnunciado();
        if(codigoFragmento == 3){
            ruta1 = listaPreguntas.get(idPregunta).getRuta1();
            ruta2 = listaPreguntas.get(idPregunta).getRuta2();
            ruta3 = listaPreguntas.get(idPregunta).getRuta3();
            ruta4 = listaPreguntas.get(idPregunta).getRuta4();
        }else{
            r1 = listaPreguntas.get(idPregunta).getRespuesta1();
            r2 = listaPreguntas.get(idPregunta).getRespuesta2();
            r3 = listaPreguntas.get(idPregunta).getRespuesta3();
            r4 = listaPreguntas.get(idPregunta).getRespuesta4();
        }

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
                case 3:
                    transicionarImagen(enunciado, ruta1, ruta2, ruta3, ruta4, respuestaCorrecta);
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
                case 3:
                    transicionarImagen(enunciado, ruta1, ruta2, ruta3, ruta4, respuestaCorrecta);
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
                case 3:
                    transicionarImagen(enunciado, ruta1, ruta2, ruta3, ruta4, respuestaCorrecta);
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
                case 3:
                    transicionarImagen(enunciado, ruta1, ruta2, ruta3, ruta4, respuestaCorrecta);
                    break;
            }
        }else{
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
                case 3:
                    fpi.nuevaPreguntaImagen(enunciado,ruta1,ruta2,ruta3,ruta4,respuestaCorrecta);
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
    public void transicionarImagen(String enunciado, int r1, int r2,int r3,int r4,String rC){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaccion = fragmentManager.beginTransaction();
        transaccion.replace(R.id.layu, fpi);
        transaccion.commitNow();
        fpi.nuevaPreguntaImagen(enunciado, r1, r2, r3, r4, rC);
        fragmentoActual = 3;
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

    @Override
    public void onRespuestaImagen(boolean correcto) {
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