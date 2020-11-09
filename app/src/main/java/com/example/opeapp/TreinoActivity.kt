package com.example.opeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class TreinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treino)

        var treino = intent.getSerializableExtra("treino")

        Toast.makeText(this, "$treino", Toast.LENGTH_LONG).show()
    }
}