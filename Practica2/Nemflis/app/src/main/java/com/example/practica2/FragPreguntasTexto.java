package com.example.practica2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragPreguntasTexto extends Fragment {

    private FragPTextoListener listener;

    TextView enunciadoTexto;
    Button botRespuesta1, botRespuesta2, botRespuesta3, botRespuesta4;
    String respuestaCorrecta;


    public FragPreguntasTexto() {
        // Required empty public constructor
    }

    public interface FragPTextoListener {
        void onRespuestaTexto(boolean correcto);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_preguntas_texto, container, false);

        botRespuesta1 = v.findViewById(R.id.botRespuesta1);
        botRespuesta2 = v.findViewById(R.id.botRespuesta2);
        botRespuesta3 = v.findViewById(R.id.botRespuesta3);
        botRespuesta4 = v.findViewById(R.id.botRespuesta4);
        enunciadoTexto = v.findViewById(R.id.enunciadoTexto);


        botRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta1.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaTexto(true);
                }else{
                    listener.onRespuestaTexto(false);
                }
            }
        });

        botRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta2.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaTexto(true);
                }else{
                    listener.onRespuestaTexto(false);
                }
            }
        });

        botRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta3.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaTexto(true);
                }else{
                    listener.onRespuestaTexto(false);
                }
            }
        });

        botRespuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta4.getText().equals(respuestaCorrecta) ){
                    listener.onRespuestaTexto(true);
                }else{
                    listener.onRespuestaTexto(false);
                }
            }
        });
        return v;
    }

    public void nuevaPreguntaTexto(String enunciado, String r1, String r2,String r3,String r4,String rC){
        enunciadoTexto.setText(enunciado);
        botRespuesta1.setText(r1);
        botRespuesta2.setText(r2);
        botRespuesta3.setText(r3);
        botRespuesta4.setText(r4);
        respuestaCorrecta = rC;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragPTextoListener){
            listener = (FragPTextoListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + " tiene que implementar FragPTextoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}