package com.example.opeapp

object TreinoService {

    fun getTreinos():List<Treino> {
        val treinos = mutableListOf<Treino>()
        for (i in 1..10) {
            val t = Treino()
            t.nome = "Treino $i"
            t.ementa = "Ementa $i"
            t.professor = "Professor $i"
            t.foto = "https://img1.gratispng.com/20180403/yvq/kisspng-world-taekwondo-martial-arts-desktop-wallpaper-spo-martial-arts-5ac37ed3b83dd7.7138693215227614277547.jpg"

            treinos.add(t)
        }
        return treinos
    }
}