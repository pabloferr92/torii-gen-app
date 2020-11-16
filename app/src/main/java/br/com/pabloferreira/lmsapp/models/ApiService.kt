package br.com.pabloferreira.lmsapp.models

import android.util.Log
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object  {
        val TAG = "LOG_TORII"

        fun getRetrofitInstance(path : String) : Retrofit {
            Log.d(TAG, "MOSTRANDO URL chamada na API: " + path.toString())
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

            /** Retorna uma Instância do Client Retrofit para Requisições
     * @param path Caminho Principal da API
     */

}
