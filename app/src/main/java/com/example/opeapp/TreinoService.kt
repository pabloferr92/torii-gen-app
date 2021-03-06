package com.example.opeapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.net.URL

object TreinoService {

    val host = "https://henriquel.pythonanywhere.com"
    val TAG = "WS_Torii Gen"

    fun getTreinos(context: Context):List<Treino> {

        //val url = "$host/treinos"
        //val json = HttpHelper.get(url)

        //Log.d(TAG, json)

        //return parserJson<List<Treino>>(json)

        val dao = DatabaseManager.getTreinoDAo()
        return dao.findAll()
    }

    fun save(treino: Treino) {
        //HttpHelper.post(
          //  "$host/treinos",
            //GsonBuilder().create().toJson(treino))
        val dao = DatabaseManager.getTreinoDAo()
        dao.insert(treino)
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}