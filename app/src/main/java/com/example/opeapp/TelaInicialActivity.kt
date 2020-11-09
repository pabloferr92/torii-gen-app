package com.example.opeapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.ThemedSpinnerAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.math.log

class TelaInicialActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        progressBar_tela_inicial.setVisibility(View.INVISIBLE)

        val args = intent.extras
        val nome_usuario = args?.getString("nome_usuario")
        Toast.makeText(this, "Usuário: $nome_usuario", Toast.LENGTH_LONG).show()

        mensagemInicial.text = "Olá ${nome_usuario.toString().toUpperCase()} Seja Bem-vindo ao Torii-Gen 1.0"


        setSupportActionBar(toolbar_view)
        supportActionBar?.title = "Tela Inicial"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnAulas.setOnClickListener {

            val intent = Intent(this, RandomActivity::class.java)
            intent.putExtra("activity_title","Aulas")
            startActivity(intent)
            }

        btnAlunos.setOnClickListener {

            val intent = Intent(this, RandomActivity::class.java)
            intent.putExtra("activity_title","Alunos")
            startActivity(intent)
        }

        btnProfessores.setOnClickListener {

            val intent = Intent(this, RandomActivity::class.java)
            intent.putExtra("activity_title","Professores")
            startActivity(intent)
        }


        }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {


            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                var texto_busca = query
                Toast.makeText(this@TelaInicialActivity, query.toString(), Toast.LENGTH_LONG).show()

                return false
            }


        })
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        if (itemId == R.id.action_buscar) {

            Toast.makeText(this,"texto_busca", Toast.LENGTH_LONG).show()
        }
        else if (itemId == R.id.action_adicionar) {
            Toast.makeText(this, "Adicionar Treino", Toast.LENGTH_LONG).show()

            val intent = Intent(this, AdicionarTurma::class.java)
            startActivity(intent)
        }
        else if (itemId == R.id.action_atualizar) {
            Toast.makeText(this, "Atualizando página", Toast.LENGTH_LONG).show()
            progressBar_tela_inicial.setVisibility(View.VISIBLE)
            Thread.sleep(10000)
            progressBar_tela_inicial.setVisibility(View.INVISIBLE)
        } else if (itemId == R.id.action_config) {
            Toast.makeText(this, "Botão Config", Toast.LENGTH_LONG).show()
            val intent = Intent(this, TelaConfigActivity::class.java)
            startActivity(intent)
        } else if (itemId == android.R.id.home) {
            Toast.makeText(this, "Voltar para Home", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        return super.onOptionsItemSelected(item)

    }
}