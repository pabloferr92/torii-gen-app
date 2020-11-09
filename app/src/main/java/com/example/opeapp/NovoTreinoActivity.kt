package com.example.opeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_novo_treino.*

class NovoTreinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_treino)

        botaoSalvarTreino.setOnClickListener {
            Thread {
                val treino = Treino()
                treino.nome = nomeTreino.text.toString()
                treino.treinador = treinador.text.toString()
                treino.foto = foto.text.toString()
                treino.ementa = ementa.text.toString()

                TreinoService.save(treino)
                runOnUiThread{
                    finish()
                }

            }.start()
        }
    }
}