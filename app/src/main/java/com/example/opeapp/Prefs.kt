package com.example.opeapp

import android.content.SharedPreferences

object Prefs {

    private fun prefs(): SharedPreferences{
        val contexto = ToriiApplication.getInstance().applicationContext
        return contexto.getSharedPreferences("Torii Gen", 0)
    }

    fun getString(flag: String): String? {
        return prefs().getString(flag, "")
    }

    fun setString(flag: String, valor: String) {
        prefs().edit().putString(flag, valor).apply()
    }

    fun getBoolean(flag: String): Boolean {
        return prefs().getBoolean(flag, false)
    }

    fun setBoolean(flag: String, valor: Boolean) {
        prefs().edit().putBoolean(flag, valor).apply()
    }
}