package br.com.pabloferreira.lmsapp.listener

import br.com.pabloferreira.lmsapp.models.TokenModel

interface LoginListener {

    fun onFailure(str: String)

    fun onSuccess(model : TokenModel)
}