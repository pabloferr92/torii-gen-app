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
                val d = Treino()
                d.nome = nomeTreino.text.toString()
                d.treinador = treinador.text.toString()
                d.foto = foto.text.toString()
                d.ementa = ementa.text.toString()

                TreinoService.save(d)
                runOnUiThread{
                    finish()
                }

            }.start()
        }
    }
}