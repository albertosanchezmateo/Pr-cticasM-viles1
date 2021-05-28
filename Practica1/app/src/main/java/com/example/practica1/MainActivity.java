package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private RadioGroup radioGroup;
    private RadioButton videojuegos;
    private RadioButton peliculas;
    private Button jugar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        videojuegos = (RadioButton) findViewById(R.id.videojuegos);
        peliculas = (RadioButton) findViewById(R.id.peliculas);

        jugar = (Button) findViewById(R.id.boton_jugar);

        jugar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int seleccion = radioGroup.getCheckedRadioButtonId();
        if(seleccion == videojuegos.getId()){
            Intent intent = new Intent( this, PreguntasVideojuegos.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent( this, PreguntasPeliculas.class);
            startActivity(intent);
        }
    }
}