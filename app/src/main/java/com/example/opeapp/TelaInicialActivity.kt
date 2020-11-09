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
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.navigation_view.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var treinos = listOf<Treino>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        this.genericLayoutMenu = layoutMenuLateral
        this.genericMenuLateral = menu_lateral


        val args = intent.extras
        val nome_usuario = args?.getString("nome_usuario")
        val nome = args?.getString("nome")
        val numero = intent.getIntExtra("nome",0)
        Toast.makeText(this, "Usuário: $nome_usuario", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Numero: $numero", Toast.LENGTH_LONG).show()
        setSupportActionBar(toolbar_view)
        supportActionBar?.title = "Treinos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        (menu.findItem(R.id.search).actionView as SearchView).apply {
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        if (itemId == R.id.action_buscar) {
            Toast.makeText(this, "Botão Buscar", Toast.LENGTH_LONG).show()
            btnsearchview.setVisibility(View.VISIBLE)
        } else if (itemId == R.id.action_atualizar) {
            Toast.makeText(this, "Botão Atualizar", Toast.LENGTH_LONG).show()
        } else if (itemId == R.id.action_config) {
            Toast.makeText(this, "Botão Config", Toast.LENGTH_LONG).show()
            val intent = Intent(this, TelaConfigActivity::class.java)
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