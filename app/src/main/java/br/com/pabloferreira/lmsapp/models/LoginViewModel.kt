package br.com.pabloferreira.lmsapp.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pabloferreira.lmsapp.Prefs
import br.com.pabloferreira.lmsapp.listener.LoginListener

class LoginViewModel {
    /**
     * Faz login usando API
     */

    private val mLogin = MutableLiveData<Boolean>()
//    private val mSharedPrefences = Secu()
    var login : LiveData<Boolean> = mLogin


    private val mTokenRepository = TokenRepository()

    fun doLogin(email: String, password: String) {
        Log.d("LOG_TORII", "enviando para repositorio"+ email +" "+ password)
        mTokenRepository.login(email,password, object : LoginListener{
            override fun onFailure(str: String) {
                val s = "erro"
                Prefs.setString("ope_token", "")
                mLogin.value = false
            }

            override fun onSuccess(model: TokenModel) {
                val s = "sucesso"
                mLogin.value = true
                if (model.access==""){
                    Prefs.setString("ope_token", "")
                }else{
                    Prefs.setString("ope_token", model.access)
                }


            }

        })
    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {
    }

}