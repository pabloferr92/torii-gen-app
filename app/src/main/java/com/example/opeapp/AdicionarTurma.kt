package com.example.opeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class AdicionarTurma : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_turma)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Toast.makeText(this, "Adicionar Turma", Toast.LENGTH_LONG).show()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this, "Voltar para Tela de Turmas", Toast.LENGTH_LONG).show()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        if (itemId == R.id.home) {
            Toast.makeText(this, "Voltar para Tela inicial", Toast.LENGTH_LONG).show()
            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        }
        else if (itemId== R.id.home) {
            Toast.makeText(this, "Voltar para Tela inicial", Toast.LENGTH_LONG).show()
            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)

            finish()

        }




        return super.onOptionsItemSelected(item)

    }


}