package br.com.pabloferreira.lmsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_disciplina.*
import kotlinx.android.synthetic.main.login.*

class DisciplinaCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_disciplina)
        setTitle("Nova Disciplina")

        salvarDisciplina.setOnClickListener {
            val disciplina = Disciplina()
            disciplina.name = nomeDisciplina.text.toString()
            disciplina.description = ementaDisciplina.text.toString()
            taskAtualizar(disciplina)
        }
    }

    private fun taskAtualizar(disciplina: Disciplina) {
        // Thread para salvar a discilpina
        Thread {
            DisciplinaService.save(disciplina)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
