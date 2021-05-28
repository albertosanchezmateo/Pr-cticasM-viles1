package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Resultados extends AppCompatActivity implements View.OnClickListener {

    private TextView puntuacion;
    private Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Bundle bundle = getIntent().getExtras();
        int resultados = bundle.getInt("puntuacion");

        puntuacion = (TextView) findViewById(R.id.resultados);
        volver = (Button) findViewById(R.id.botVolver);

        puntuacion.setText("¡Has acertado " + resultados + " preguntas!");

        volver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
}