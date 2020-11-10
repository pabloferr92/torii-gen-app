package br.com.pabloferreira.lmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        val args = intent.extras
        //val nome = args.getString("Aulas")
        Toast.makeText(this, "Parâmetro: "+ args, Toast.LENGTH_LONG).show()
        var activity_name = intent.getStringExtra("activity_title")
        setTitle(activity_name)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this, "Voltar para Tela de Turmas", Toast.LENGTH_LONG).show()

        return true
    }
}