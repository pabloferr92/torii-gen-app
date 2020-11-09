package com.example.opeapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TreinoDAO {

    @Query ("SELECT * FROM treinos where id=:id")
    fun getById(id: Long): Treino?

    @Query("SELECT * FROM treinos")
    fun findAll(): List<Treino>

    @Insert
    fun insert(treino: Treino)

    @Delete
    fun delete(treino: Treino)
}