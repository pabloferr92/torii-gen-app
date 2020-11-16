package br.com.pabloferreira.lmsapp.models

import com.google.gson.annotations.SerializedName


data class Alunos (
    @SerializedName("id")
    var id : Int,
    @SerializedName("username")
    var username : String,
    @SerializedName("email")
    var email : String,
)

