package com.example.practica2;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class FragPreguntaImagen extends Fragment {

    private FragPImagenListener listener;

    TextView enunciadoTexto;
    ImageButton respuesta1, respuesta2, respuesta3, respuesta4;
    String respuestaCorrecta;

    public FragPreguntaImagen(){

    }

    public interface FragPImagenListener {
        void onRespuestaImagen(boolean correcto);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_frag_pregunta_imagen, container, false);

        respuesta1 = v.findViewById(R.id.respuestaImagen1);
        respuesta2 = v.findViewById(R.id.respuestaImagen2);
        respuesta3 = v.findViewById(R.id.respuestaImagen3);
        respuesta4 = v.findViewById(R.id.respuestaImagen4);
        enunciadoTexto = v.findViewById((R.id.enunciadoImagen));

        respuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(respuestaCorrecta.equals("0")){
                    listener.onRespuestaImagen(true);
                }else{
                    listener.onRespuestaImagen(false);
                }
            }
        });

        respuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(respuestaCorrecta.equals("1")){
                    listener.onRespuestaImagen(true);
                }else{
                    listener.onRespuestaImagen(false);
                }
            }
        });

        respuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(respuestaCorrecta.equals("2")){
                    listener.onRespuestaImagen(true);
                }else{
                    listener.onRespuestaImagen(false);
                }
            }
        });

        respuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(respuestaCorrecta.equals("3")){
                    listener.onRespuestaImagen(true);
                }else{
                    listener.onRespuestaImagen(false);
                }
            }
        });
        return v;
    }

    public void nuevaPreguntaImagen(String enunciado, int ruta1, int ruta2, int ruta3, int ruta4, String rC ){
        enunciadoTexto.setText(enunciado);
        respuesta1.setImageResource(ruta1);
        respuesta2.setImageResource(ruta2);
        respuesta3.setImageResource(ruta3);
        respuesta4.setImageResource(ruta4);
        respuestaCorrecta = rC;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragPreguntasTexto.FragPTextoListener){
            listener = (FragPreguntaImagen.FragPImagenListener) context;
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
