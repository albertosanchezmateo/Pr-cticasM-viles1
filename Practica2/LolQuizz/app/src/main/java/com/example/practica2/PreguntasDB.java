package com.example.practica2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = Pregunta.class, version = 1
)

public abstract class PreguntasDB extends RoomDatabase {

    private static PreguntasDB baseDeDatos;

    private static String DATABASE_NAME = "Preguntas";

    public synchronized static PreguntasDB getInstance(Context context){
        if(baseDeDatos == null){
            baseDeDatos = Room.databaseBuilder(context.getApplicationContext(),
                    PreguntasDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return baseDeDatos;
    }


    public abstract PreguntaDao preguntaDao();

}
