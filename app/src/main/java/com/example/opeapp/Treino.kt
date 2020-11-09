package com.example.opeapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "treinos")
class Treino: Serializable {

    @PrimaryKey
    var id: Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var treinador = ""

    override fun toString(): String {
        return "Treino(nome='$nome')"
    }
}