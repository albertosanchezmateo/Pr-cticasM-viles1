package com.example.practica2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class FragPreguntaVideo extends Fragment {

    private FragPVideoListener listener;
    private Context contexto;

    VideoView enunciado;
    String path;

    Button botRespuesta1, botRespuesta2, botRespuesta3, botRespuesta4;
    String respuestaCorrecta;

    public FragPreguntaVideo() {
        // Required empty public constructor
    }

    public interface FragPVideoListener {
        void onRespuestaVideo(boolean correcto);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frag_pregunta_video, container, false);

        enunciado = (VideoView) v.findViewById(R.id.videoEnunciado);
        botRespuesta1 = v.findViewById(R.id.botRVideo1);
        botRespuesta2 = v.findViewById(R.id.botRVideo2);
        botRespuesta3 = v.findViewById(R.id.botRVideo3);
        botRespuesta4 = v.findViewById(R.id.botRVideo4);

        MediaController mediaController = new MediaController(contexto);
        enunciado.setMediaController(mediaController);
        mediaController.setAnchorView(enunciado);



        botRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta1.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaVideo(true);
                }else{
                    listener.onRespuestaVideo(false);
                }
            }
        });

        botRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta2.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaVideo(true);
                }else{
                    listener.onRespuestaVideo(false);
                }
            }
        });

        botRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta3.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaVideo(true);
                }else{
                    listener.onRespuestaVideo(false);
                }
            }
        });

        botRespuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta4.getText().equals(respuestaCorrecta) ){
                    listener.onRespuestaVideo(true);
                }else{
                    listener.onRespuestaVideo(false);
                }
            }
        });
        return v;
    }

    public void nuevaPreguntaVideo(String ruta, String r1, String r2,String r3,String r4,String rC){
        enunciado.setVideoURI(Uri.parse(ruta));
        botRespuesta1.setText(r1);
        botRespuesta2.setText(r2);
        botRespuesta3.setText(r3);
        botRespuesta4.setText(r4);
        respuestaCorrecta = rC;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contexto = context;
        if(context instanceof FragPreguntasTexto.FragPTextoListener){
            listener = (FragPreguntaVideo.FragPVideoListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + " tiene que implementar FragPVideoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}