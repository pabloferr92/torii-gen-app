package br.com.pabloferreira.lmsapp.models

import com.google.gson.annotations.SerializedName

class TokenModel {
    @SerializedName("access")
    var access : String = ""
    @SerializedName("refresh")
    var refresh : String = ""

}