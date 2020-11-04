package com.example.opeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_constraint)


        //imgLogin.setImageResource(R.drawable.imagen_login)

        Prefs.setString("nome", "Fulano")
        Prefs.setBoolean("treinador", true)
        Prefs.setString("curso", "...")


        textoInicial.setText("Bem vindo ao Torii Gen")

        campoUsuario.setText(Prefs.getString("usuario"))
        campoSenha.setText(Prefs.getString("senha"))
        checkLogin.isChecked = Prefs.getBoolean("lembrar")

        botaoLogin.setOnClickListener {onClickLogin() }

        }


            //progress.visibility = View.INVISIBLE
        fun onClickLogin(){
            val valorUsuario = campoUsuario.text.toString()
            val valorSenha = campoSenha.text.toString()
            //Toast.makeText(this, "Usuário $valorUsuario; Senha $valorSenha", Toast.LENGTH_LONG).show()




            Prefs.setBoolean("lembrar", checkLogin.isChecked)
            if (checkLogin.isChecked) {
                Prefs.setString("usuario", valorUsuario)
                Prefs.setString("senha", valorSenha)
            } else {
                Prefs.setString("usuario", "")
                Prefs.setString("senha", "")
            }

            val intent = Intent(this, TelaInicialActivity::class.java)
            val params = Bundle()
            params.putString("nome_usuario", valorUsuario)
            params.putInt("numero", 10)

            intent.putExtras(params)

            //startActivity(intent)
            if (valorUsuario =="aluno" && valorSenha =="impacta") {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Login ou senha inválidos", Toast.LENGTH_LONG).show()
                }
            }
        }


            //progress.visibility = View.GONE



