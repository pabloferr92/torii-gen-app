package com.example.opeapp

import java.io.Serializable


class Treino: Serializable {

    var id: Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var treinador = ""

    override fun toString(): String {
        return "Treino(nome='$nome')"
    }
}