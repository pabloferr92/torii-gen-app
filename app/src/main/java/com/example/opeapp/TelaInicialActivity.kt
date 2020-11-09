package com.example.opeapp

import android.content.Context
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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.navigation_view.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.math.log

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var treinos = listOf<Treino>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        progressBar_tela_inicial.setVisibility(View.INVISIBLE)
        this.genericLayoutMenu = layoutMenuLateral
        this.genericMenuLateral = menu_lateral


        val args = intent.extras
        val nome_usuario = args?.getString("nome_usuario")
        val nome = args?.getString("nome")
        val numero = intent.getIntExtra("nome",0)
        Toast.makeText(this, "Usuário: $nome_usuario", Toast.LENGTH_LONG).show()

        mensagemInicial.text = "Olá ${nome_usuario.toString().toUpperCase()} Seja Bem-vindo ao Torii-Gen 1.0"


        Toast.makeText(this, "Numero: $numero", Toast.LENGTH_LONG).show()
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

        configuraMenuLateral()
        recyclerTreinos?.layoutManager = LinearLayoutManager(this)
        recyclerTreinos?.itemAnimator = DefaultItemAnimator()
        recyclerTreinos?.setHasFixedSize(true)

        Toast.makeText(this, Prefs.getString("nome"), Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        taskTreinos()
    }

    fun enviaNotificacao(treino: Treino) {
        val intent = Intent(this, TreinoActivity::class.java)
        intent.putExtra("treino", treino)
        NotificationUtil.create(this, 1, intent, "Torii Gen", "Você tem nova atividade na ${treino.nome}")
    }

    fun taskTreinos() {
        Thread {
        this.treinos = TreinoService.getTreinos(this )
        runOnUiThread {
        recyclerTreinos?.adapter = TreinoAdapter(this.treinos) {onClickTreino(it)}
            enviaNotificacao(this.treinos.get(0))
            }
        }.start()
    }

    fun onClickTreino(treino: Treino) {
        var it = Intent(this, TreinoActivity::class.java)
        it.putExtra("treino", treino)

        startActivity(it)
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
        } else if (itemId == R.id.action_adicionar) {
            val intent = Intent(this, NovoTreinoActivity::class.java)
            startActivity(intent)
        }

         else if (itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }
}