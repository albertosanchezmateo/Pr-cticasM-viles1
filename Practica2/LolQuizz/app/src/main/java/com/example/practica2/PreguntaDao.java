package com.example.practica2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PreguntaDao {

    @Query("SELECT * FROM Pregunta")
    List<Pregunta> getAll();

    @Insert
    void insert(Pregunta pregunta);

    @Query("DELETE FROM Pregunta")
    void deleteAll();



}
