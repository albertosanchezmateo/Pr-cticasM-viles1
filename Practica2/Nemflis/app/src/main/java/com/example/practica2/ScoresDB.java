package com.example.practica2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = Score.class, version = 2
)

public abstract class ScoresDB extends RoomDatabase {

    private static ScoresDB baseDeDatosScores;

    private static String DATABASE_NAME = "Scores";

    public synchronized static ScoresDB getInstance(Context context){
        if(baseDeDatosScores == null){
            baseDeDatosScores = Room.databaseBuilder(context.getApplicationContext(),
                    ScoresDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return baseDeDatosScores;
    }


    public abstract ScoresDao scoresDao();

}
