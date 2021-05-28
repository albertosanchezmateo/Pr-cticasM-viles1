package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PreguntasPeliculas extends AppCompatActivity implements View.OnClickListener {

    int pregunta = 1;
    int puntuacion = 0;

    private ImageButton respuesta1, respuesta2, respuesta3, respuesta4;
    private TextView enunciado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_peliculas);

        enunciado = (TextView) findViewById(R.id.enunciado);
        respuesta1 = (ImageButton) findViewById(R.id.respuesta1);
        respuesta2 = (ImageButton) findViewById(R.id.respuesta2);
        respuesta3 = (ImageButton) findViewById(R.id.respuesta3);
        respuesta4 = (ImageButton) findViewById(R.id.respuesta4);

        respuesta1.setOnClickListener(this);
        respuesta2.setOnClickListener(this);
        respuesta3.setOnClickListener(this);
        respuesta4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (pregunta){
            case 1:
                if(R.id.respuesta1 == v.getId()){
                    puntuacion++;
                    Toast.makeText(this, "¡Has acertado!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "¡Has fallado!, la respuesta correcta era Frodo Bolson (Superior Izquierda)", Toast.LENGTH_SHORT).show();
                }
                enunciado.setText("¿Quién es el protagonista de \n 'Piratas del Caribe'?");
                respuesta1.setImageResource(R.drawable.pairot);
                respuesta2.setImageResource(R.drawable.sparrow);
                respuesta3.setImageResource(R.drawable.pirata);
                respuesta4.setImageResource(R.drawable.barbosa);
                pregunta++;
                break;
            case 2:
                if(R.id.respuesta2 == v.getId()){
                    puntuacion++;
                    Toast.makeText(this, "¡Has acertado!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "¡Has fallado!, la respuesta correcta era Jack Sparrow (Superior Derecha)", Toast.LENGTH_SHORT).show();
                }
                enunciado.setText("¿Quién utiliza como arma principal un martillo?");
                respuesta1.setImageResource(R.drawable.bob);
                respuesta2.setImageResource(R.drawable.guason);
                respuesta3.setImageResource(R.drawable.capi);
                respuesta4.setImageResource(R.drawable.thor);
                pregunta++;
                break;
            case 3:
                if(R.id.respuesta4 == v.getId()){
                    puntuacion++;
                    Toast.makeText(this, "¡Has acertado!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "¡Has fallado!, la respuesta correcta era Thor (Inferior Derecha)", Toast.LENGTH_SHORT).show();
                }
                enunciado.setText("¿Cuál de estos personajes es de \n 'El señor de los anillos'?");
                respuesta1.setImageResource(R.drawable.frodo);
                respuesta2.setImageResource(R.drawable.darthvader);
                respuesta3.setImageResource(R.drawable.ironman);
                respuesta4.setImageResource(R.drawable.shrek);
                pregunta = 1;
                Resultados();
                break;
        }
    }

    public void Resultados(){
        Intent resultados = new Intent(this, Resultados.class);
        resultados.putExtra("puntuacion", puntuacion);
        startActivity(resultados);
    }
}