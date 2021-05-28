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

import java.io.IOException;

public class FragPreguntaAudio extends Fragment {

    private FragPreguntaAudio.FragPAudioListener listener;
    private Context contexto;

    Button botRespuesta1, botRespuesta2, botRespuesta3, botRespuesta4;
    ImageButton play, pause, stop;
    String respuestaCorrecta;
    MediaPlayer mediaplayer;


    public FragPreguntaAudio() {
        // Required empty public constructor
    }

    public interface FragPAudioListener {
        void onRespuestaAudio(boolean correcto);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frag_pregunta_audio, container, false);

        botRespuesta1 = v.findViewById(R.id.botRAudio1);
        botRespuesta2 = v.findViewById(R.id.botRAudio2);
        botRespuesta3 = v.findViewById(R.id.botRAudio3);
        botRespuesta4 = v.findViewById(R.id.botRAudio4);
        play = v.findViewById(R.id.botPlay);
        pause = v.findViewById(R.id.botPause);
        stop = v.findViewById(R.id.botStop);

        botRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta1.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaAudio(true);
                }else{
                    listener.onRespuestaAudio(false);
                }
            }
        });

        botRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta2.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaAudio(true);
                }else{
                    listener.onRespuestaAudio(false);
                }
            }
        });

        botRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta3.getText().equals(respuestaCorrecta)){
                    listener.onRespuestaAudio(true);
                }else{
                    listener.onRespuestaAudio(false);
                }
            }
        });

        botRespuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(botRespuesta4.getText().equals(respuestaCorrecta) ){
                    listener.onRespuestaAudio(true);
                }else{
                    listener.onRespuestaAudio(false);
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mediaplayer.stop();
                    mediaplayer.prepare();
                    mediaplayer.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    public void nuevaPreguntaAudio(String ruta, String r1, String r2,String r3,String r4,String rC){
        mediaplayer = MediaPlayer.create(contexto, Uri.parse(ruta));
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
            listener = (FragPreguntaAudio.FragPAudioListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + " tiene que implementar FragPAudioListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}