package br.com.pabloferreira.lmsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import br.com.pabloferreira.lmsapp.models.LoginViewModel
import kotlinx.android.synthetic.main.login.*

class MainActivity : br.com.pabloferreira.lmsapp.DebugActivity() {
    private lateinit var mViewModel: LoginViewModel

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
//        observe()
        Prefs.setString("ope_token", "")


        // encontra objeto pelo id
        campo_imagem.setImageResource(R.drawable.imagem_login)

        texto_login.text = getString(R.string.mensagem_login)


        // evento no botao de login forma 1
//        botao_login.setOnClickListener {
//            val valorUsuario = campo_usuario.text.toString()
//            val valorSenha = campo_senha.text.toString()
//            Toast.makeText(this, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()
//        }

        // segunda forma: delegar para método
        botao_login.setOnClickListener { onClickLogin() }

        progressBar.visibility = View.INVISIBLE

        // procurar pelas preferências, se pediu para guardar usuário e senha
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")
            campo_usuario.setText(lembrarNome)
            campo_senha.setText(lembrarSenha)
            checkBoxLogin.isChecked = lembrar

        }
    }

    fun onClickLogin() {

        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        Log.d("LOG_TORII", "usuario"+campo_usuario)

        // armazenar valor do checkbox
        Prefs.setBoolean("lembrar", checkBoxLogin.isChecked)
        // verificar se é para pembrar nome e senha
        if (checkBoxLogin.isChecked) {
            Prefs.setString("lembrarNome", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)
        } else {
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        val login_view = LoginViewModel()
        val token = login_view.doLogin(valorUsuario,valorSenha)

//        // criar intent
//        val intent = Intent(context, TelaInicialActivity::class.java)
//        // colocar parâmetros (opcional)
//        val params = Bundle()
//        params.putString("nome", "Fernando Sousa")
//        intent.putExtras(params)
//
//        // enviar parâmetros simplificado
//        intent.putExtra("numero", 10)
//
//        // fazer a chamada
//        //startActivity(intent)
//
//        // fazer a chamada esperando resultado
//        startActivity(intent)

        val token_pref = Prefs.getString("ope_token")
        Toast.makeText(this, token_pref.toString(), Toast.LENGTH_LONG).show()

        if (token_pref!=""){
            val intent = Intent(context, TelaInicialActivity::class.java)
            startActivity(intent)
        } else{
            Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_LONG).show()

        }



    }

    fun observe(){
        mViewModel.login.observe(this, Observer {
            if (it){
                startActivity(Intent(this, TelaInicialActivity::class.java))
                Toast.makeText(this, "Login com sucesso", Toast.LENGTH_LONG).show()

            } else{
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }


    override fun onResume() {
        super.onResume()
        abrirDisciplina()
        Prefs.setString("ope_token", "")

    }

    fun abrirDisciplina() {
        // verificar se existe  id da disciplina na intent
        if (intent.hasExtra("disciplinaId")) {
            Thread {
                var disciplinaId = intent.getStringExtra("disciplinaId")?.toLong()!!
                val disciplina = DisciplinaService.getDisciplina(this, disciplinaId)
                runOnUiThread {
                    val intentDisciplina = Intent(this, DisciplinaActivity::class.java)
                    intentDisciplina.putExtra("disciplina", disciplina)
                    startActivity(intentDisciplina)
                }
            }.start()
        }

    }
}
