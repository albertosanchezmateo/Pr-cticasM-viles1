package com.example.practica2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoresDao {

    @Query("SELECT * FROM Score ORDER BY CAST(puntuacion as float) DESC LIMIT 5")
    List<Score> getAllScores();

    @Insert
    void insert(Score score);

    @Query("DELETE FROM Score")
    void deleteAll();

    @Query("UPDATE Score SET puntuacion = :pun WHERE nombre = :nom ")
    void actualizar(String nom, double pun);

    @Query("SELECT * FROM Score WHERE nombre = :nom")
    Score buscarNombre(String nom);

}
