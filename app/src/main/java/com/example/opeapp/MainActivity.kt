package com.example.opeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        progress.setVisibility(View.INVISIBLE)


        //campo_imagem.setImageResource(R.drawable.imagen_login)

        //textoInicial.text("Bem vindo ao Torii Gen")


        botaoLogin.setOnClickListener {


            val valorUsuario = campoUsuario.text.toString()
            val valorSenha = campoSenha.text.toString()
            //Toast.makeText(this, "Usuário $valorUsuario; Senha $valorSenha", Toast.LENGTH_LONG).show()

            val intent = Intent(this, TelaInicialActivity::class.java)
            val params = Bundle()
            params.putString("nome_usuario", valorUsuario)
            params.putInt("numero", 10)

            intent.putExtras(params)

            //startActivity(intent)
            if (valorUsuario =="aluno" && valorSenha =="impacta") {
                progress.setVisibility(View.VISIBLE)
                startActivity(intent)

            }
            else {
                progress.setVisibility(View.VISIBLE)
                Toast.makeText(this,"Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
                Thread.sleep(2000)
                progress.setVisibility(View.INVISIBLE)
            }
        }


        }

    }
