package com.example.opeapp

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: ToriiDatabase

    init {
        val context = ToriiApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            context,
            ToriiDatabase::class.java,
            "ope.sqlite"
        ).build()
    }

    fun getTreinoDAo(): TreinoDAO {
        return dbInstance.treinoDAO()
    }
}