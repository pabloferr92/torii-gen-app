package br.com.pabloferreira.lmsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pabloferreira.lmsapp.models.AlunoInterface
import br.com.pabloferreira.lmsapp.models.Alunos
import br.com.pabloferreira.lmsapp.models.ApiService
import kotlinx.android.synthetic.main.activity_aluno.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlunoActivity : DebugActivity() {

    private val context: Context get() = this
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    private var alunos = listOf<Alunos>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "LOG_TORII"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluno)
        val args = intent.extras
        //val nome = args.getString("Aulas")
        Toast.makeText(this, "Parâmetro: "+ args, Toast.LENGTH_LONG).show()
        var activity_name = intent.getStringExtra("activity_title")
        setTitle(activity_name)
        supportActionBar?.title = "Alunos"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerAlunos?.layoutManager = LinearLayoutManager(context)
        recyclerAlunos?.itemAnimator = DefaultItemAnimator()
        recyclerAlunos?.setHasFixedSize(true)

        getData()

    }

    fun onClickAluno(aluno: Alunos) {
        Toast.makeText(context, "Aluno: ${aluno.username}", Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            // iniciar activity de cadastro
            val intent = Intent(context, DisciplinaCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getData() {
        val retrofitClient = ApiService
        .getRetrofitInstance("http://192.168.0.234:8000/api/")

        val endpoint = retrofitClient.create(AlunoInterface::class.java)
        val callback = endpoint.getAlunos()

        callback.enqueue(object : Callback<List<Alunos>> {
            override fun onFailure(call: Call<List<Alunos>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Alunos>>, response: Response<List<Alunos>>) {
                Log.d(TAG.toString(), "Mostrando o resultado da response"+ response.body().toString())
                response.body()?.forEach {

                    textView_alunos.text = textView_alunos.text.toString().plus(it.username)

                }
                var response_alunos = response.body();
                recyclerAlunos.adapter = AlunoAdapter(response_alunos as ArrayList<Alunos>) { onClickAluno(it) } }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this, "Voltar para Tela de Turmas", Toast.LENGTH_LONG).show()

        return true
    }
}