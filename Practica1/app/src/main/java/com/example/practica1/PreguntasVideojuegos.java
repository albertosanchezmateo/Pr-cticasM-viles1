package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PreguntasVideojuegos extends AppCompatActivity implements View.OnClickListener{

    int pregunta = 1;
    int puntuacion = 0;
    private ImageView imagenEnunciado;
    private TextView enunciado;
    private CheckBox respuesta1, respuesta2, respuesta3;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_videojuegos);

        imagenEnunciado = (ImageView) findViewById(R.id.imagenEnunciado);
        enunciado = (TextView) findViewById(R.id.enunciado);
        respuesta1 = (CheckBox) findViewById(R.id.respuesta1);
        respuesta2 = (CheckBox) findViewById(R.id.respuesta2);
        respuesta3 = (CheckBox) findViewById(R.id.respuesta3);
        siguiente = (Button) findViewById(R.id.botSiguiente);

        siguiente.setOnClickListener(this);
        imagenEnunciado.setImageResource(R.drawable.peacekeeper);
    }

    @Override
    public void onClick(View v) {
        switch (pregunta){
            case 1:
                if(ComprobarSoloUna()) {
                    if(respuesta2.isChecked() && !respuesta3.isChecked() && !respuesta1.isChecked()){
                        puntuacion++;
                        Toast.makeText(this, "¡Has acertado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "¡Has fallado!, la respuesta correcta era Peacekeeper", Toast.LENGTH_SHORT).show();
                    }
                    enunciado.setText("¿A qué saga de videojuegos pertenece este personaje?");
                    imagenEnunciado.setImageResource(R.drawable.huesitos);
                    respuesta1.setText("Red Dead Redemption");
                    respuesta2.setText("Final Fantasy");
                    respuesta3.setText("Super Mario Bros");
                    ResetCheckbox();
                    pregunta++;
                }else{
                    Toast.makeText(this, "Respuesta inválida, marca una por favor", Toast.LENGTH_SHORT).show();
                    ResetCheckbox();
                }
                break;
            case 2:
                if(ComprobarSoloUna()) {
                    if (respuesta3.isChecked() && !respuesta2.isChecked() && !respuesta1.isChecked()) {
                        puntuacion++;
                        Toast.makeText(this, "¡Has acertado!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "¡Has fallado!, la respuesta correcta era Super Mario Bros", Toast.LENGTH_SHORT).show();
                    }
                    enunciado.setText("¿Cómo se llama este campeón?");
                    imagenEnunciado.setImageResource(R.drawable.akali);
                    respuesta1.setText("Akali");
                    respuesta2.setText("Sonic");
                    respuesta3.setText("Solid Snake");
                    ResetCheckbox();
                    pregunta++;
                }else{
                    Toast.makeText(this, "Respuesta inválida, marca una por favor", Toast.LENGTH_SHORT).show();
                    ResetCheckbox();
                }
                break;
            case 3:
                if(ComprobarSoloUna()) {
                    if (respuesta1.isChecked() && !respuesta2.isChecked() && !respuesta3.isChecked()) {
                        puntuacion++;
                        Toast.makeText(this, "¡Has acertado!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "¡Has fallado!, la respuesta correcta era Akali", Toast.LENGTH_SHORT).show();
                    }
                    enunciado.setText("¿Cómo se llama este arma?");
                    imagenEnunciado.setImageResource(R.drawable.peacekeeper);
                    respuesta1.setText("PDW");
                    respuesta2.setText("Peacekeeper");
                    respuesta3.setText("MSMC");
                    ResetCheckbox();
                    pregunta = 1;
                    Resultados();
                }else{
                    Toast.makeText(this, "Respuesta inválida, marca una por favor", Toast.LENGTH_SHORT).show();
                    ResetCheckbox();
                }
                break;
        }
    }

    public void Resultados(){
        Intent resultados = new Intent(this, Resultados.class);
        resultados.putExtra("puntuacion", puntuacion);
        startActivity(resultados);
    }

    public void ResetCheckbox(){
        respuesta1.setChecked(false);
        respuesta2.setChecked(false);
        respuesta3.setChecked(false);
    }

    public boolean ComprobarSoloUna(){
        int contador = 0;
        if(respuesta1.isChecked()){
            contador++;
        }
        if(respuesta2.isChecked()){
            contador++;
        }
        if(respuesta3.isChecked()){
            contador++;
        }
        if(contador == 1){
            return true;
        }else{
            return false;
        }
    }
}