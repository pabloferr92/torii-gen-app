package br.com.pabloferreira.lmsapp.models

import android.util.Log
import android.widget.Toast
import br.com.pabloferreira.lmsapp.AlunoAdapter
import br.com.pabloferreira.lmsapp.R
import br.com.pabloferreira.lmsapp.listener.LoginListener
import kotlinx.android.synthetic.main.activity_aluno.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TokenRepository {
    val TAG = "LOG_TORII"
    val retrofitClient = ApiService
            .getRetrofitInstance("http://192.168.0.234:8000/")


    fun login(username:String, password : String, listener : LoginListener ){
        var login_info = LoginInfo(username=username,password=password)
        Log.d(TAG,"chamando login API service "+ username + password)
        val endpoint = retrofitClient.create(LoginInterface::class.java)
        val callback = endpoint.Login(login_info)

        callback.enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                //Log.d(TAG.toString(), "Mostrando o resultado da response"+ response.body().toString())
                var response_token = response.body();
                Log.d(TAG,"Mostrando token: "+response_token?.access)
                response_token?.let { listener.onSuccess(it) }
                 }
        })

    }
}