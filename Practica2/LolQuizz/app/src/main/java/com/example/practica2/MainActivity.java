package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity{

    static final int GAME_REQUEST_CODE = 1;
    FloatingActionButton fabSettings;
    TextView nJugador, nPreguntas;
    Button bJugar, bRanking;

    String nombreJugador;
    String numeroPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabSettings = (FloatingActionButton) findViewById(R.id.fabConfig);
        nJugador = (TextView) findViewById(R.id.nombreJugador);
        nPreguntas = (TextView) findViewById(R.id.numeroPreguntas);
        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent config = new Intent(MainActivity.this, Configuracion.class);
                startActivityForResult(config, GAME_REQUEST_CODE);
            }
        });
        bJugar = (Button) findViewById(R.id.botJugar);
        bJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidadPreguntas = Integer.parseInt(numeroPreguntas);
                Intent juego = new Intent(MainActivity.this, GameActivity.class);
                juego.putExtra("nombreDelJugador", nombreJugador);
                juego.putExtra("cantidadPreguntas", cantidadPreguntas);
                startActivity(juego);
            }
        });

        bRanking = findViewById(R.id.botRanking);
        bRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ranking = new Intent(MainActivity.this, Ranking.class);
                ranking.putExtra("vieneDeJugar", false);
                startActivity(ranking);
            }
        });

        try{
            FileInputStream fis = openFileInput("configuracion.txt");
            InputStreamReader miReader = new InputStreamReader(fis);
            BufferedReader miBuffer = new BufferedReader(miReader);
            nombreJugador = miBuffer.readLine();
            numeroPreguntas = miBuffer.readLine();
            fis.close();
            nPreguntas.setText("Vas a jugar a " + numeroPreguntas + " preguntas");
            if(nombreJugador.equals("")){
                nombreJugador = "Anonimo";
            }
            nJugador.setText("Estas registrado como " + nombreJugador);
        } catch (FileNotFoundException e) {
            nombreJugador = "Anonimo";
            numeroPreguntas = "5";
            nJugador.setText("Estas registrado como Anonimo");
            nPreguntas.setText("Vas a jugar a 5 preguntas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GAME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                nombreJugador = data.getStringExtra("nombreJugador");
                if(nombreJugador.length() < 1){
                    nombreJugador = "Anonimo";
                    nJugador.setText("Estas registrado como Anonimo");
                }else{
                    nJugador.setText("Estas registrado como " + nombreJugador);
                }
                numeroPreguntas = data.getStringExtra("numeroPreguntas");
                nPreguntas.setText("Vas a jugar a " + numeroPreguntas  + " preguntas");

                try {
                    FileOutputStream fos = openFileOutput("configuracion.txt", Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write(nombreJugador + "\n");
                    osw.write(numeroPreguntas);
                    osw.flush();
                    osw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}