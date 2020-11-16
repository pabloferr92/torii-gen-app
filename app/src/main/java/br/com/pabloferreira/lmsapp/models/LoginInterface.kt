package br.com.pabloferreira.lmsapp.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginInterface {

    @Headers(
            "Accept: application/json",
            "Content-type:application/json",
    )
    @POST("api-token-custom")
    fun Login(@Body loginData : LoginInfo)
              : Call<TokenModel>

}