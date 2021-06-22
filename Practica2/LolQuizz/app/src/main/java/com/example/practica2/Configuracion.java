package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fabVolver;
    EditText inputNombre;
    RadioGroup numPreguntas;
    TextView texto;
    RadioButton cinco, diez, quince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        fabVolver = (FloatingActionButton) findViewById(R.id.fabVolver);
        inputNombre = (EditText) findViewById(R.id.inputNombre);
        numPreguntas = (RadioGroup) findViewById(R.id.numPreguntas);
        texto = (TextView) findViewById(R.id.texto1);
        cinco = (RadioButton) findViewById(R.id.cincoPreg);
        diez = (RadioButton) findViewById(R.id.diezPreg);
        quince = (RadioButton) findViewById(R.id.quincePreg);

        fabVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (numPreguntas.getCheckedRadioButtonId() != -1) {
            Intent data = new Intent();
            if(numPreguntas.getCheckedRadioButtonId() == cinco.getId()){
                data.putExtra("numeroPreguntas", "5");
            }else if(numPreguntas.getCheckedRadioButtonId() == diez.getId()){
                data.putExtra("numeroPreguntas", "10");
            }else{
                data.putExtra("numeroPreguntas", "15");
            }
            data.putExtra("nombreJugador", inputNombre.getText().toString());
            setResult(RESULT_OK, data);
            finish();
        }else{
            Toast.makeText(this, "Selecciona con cuantas preguntas quieres jugar por favor", Toast.LENGTH_SHORT).show();
        }
    }
}