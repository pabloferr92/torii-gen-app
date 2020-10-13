package com.example.opeapp

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

class TelaInicialActivity : DebugActivity() {

    private var treinos = listOf<Treino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        this.genericLayoutMenu = layoutMenuLateral
        this.genericMenuLateral = menu_lateral

        val args = intent.extras
        val nome_usuario = args?.getString("nome_usuario")
        Toast.makeText(this, "Usuário: $nome_usuario", Toast.LENGTH_LONG).show()

        setSupportActionBar(toolbar_view)
        supportActionBar?.title = "Treinos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()
        recyclerTreinos?.layoutManager = LinearLayoutManager(this)
        recyclerTreinos?.itemAnimator = DefaultItemAnimator()
        recyclerTreinos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskTreinos()
    }

    fun taskTreinos() {
        this.treinos = TreinoService.getTreinos()

        recyclerTreinos?.adapter = TreinoAdapter(this.treinos) {onClickTreino(it)}
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
        } else if (itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)

    }
}