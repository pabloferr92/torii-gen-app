package br.com.pabloferreira.lmsapp.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface AlunoInterface {
    @Headers(
        "Accept: application/json",
        "Content-type:application/json",
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM3MDI3MzQ2LCJqdGkiOiIxYzQyM2E1MWVkMjI0MGYxOTQyOWViMTZiMTgzZDE3MyIsInVzZXJfaWQiOjEwNTh9.rNdq-9-otw1C_dog8w03odVp-Jt8LcoW3-J9ZzoiuSQ"
    )
    @GET("users/list_students")
    fun getAlunos() : Call<List<Alunos>>
}