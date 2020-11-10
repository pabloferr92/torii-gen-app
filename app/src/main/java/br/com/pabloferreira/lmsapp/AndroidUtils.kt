package br.com.pabloferreira.lmsapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object AndroidUtils {
    // verificar se existe algum tipo de conexão disponível
    fun isInternetDisponivel(): Boolean {
        val conexao = LMSApplication.Companion.getInstance().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val redes = conexao.allNetworks
        return redes.map { conexao.getNetworkInfo(it) }.any { it.state == NetworkInfo.State.CONNECTED }
    }
}