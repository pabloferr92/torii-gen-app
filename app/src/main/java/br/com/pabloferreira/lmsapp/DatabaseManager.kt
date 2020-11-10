package br.com.pabloferreira.lmsapp

import androidx.room.Room


object DatabaseManager {

    // singleton
    private var dbInstance: br.com.pabloferreira.lmsapp.LMSDatabase

    init {
        val appContext = br.com.pabloferreira.lmsapp.LMSApplication.Companion.getInstance().applicationContext
        br.com.pabloferreira.lmsapp.DatabaseManager.dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                br.com.pabloferreira.lmsapp.LMSDatabase::class.java, // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getDisciplinaDAO():br.com.pabloferreira.lmsapp.DisciplinaDAO {
        return br.com.pabloferreira.lmsapp.DatabaseManager.dbInstance.disciplinaDAO()
    }
}