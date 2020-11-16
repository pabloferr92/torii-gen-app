package br.com.pabloferreira.lmsapp.models

import com.google.gson.annotations.SerializedName

data class LoginInfo (
        @SerializedName("username")
        var username : String = "",
        @SerializedName("password")
        var password : String = ""
)

