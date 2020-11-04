package com.example.opeapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Treino::class), version = 1)
abstract class ToriiDatabase: RoomDatabase() {
    abstract fun treinoDAO(): TreinoDAO

}