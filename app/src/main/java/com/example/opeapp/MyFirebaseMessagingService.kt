package com.example.opeapp

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    val TAG = "firebase"
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG, "Novo token: $token")
        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        Log.d(TAG, "onMessageReceived")

        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            val corpo = mensagemRemota?.notification?.body
            Log.d(TAG, "Titulo da mensagem: $titulo")
            Log.d(TAG, "Corpo da mensagem: $corpo")

        }

            fun showNotification(mensagemRemota: RemoteMessage?) {
// Intent para abrir quando clicar na notificação
            val intent = Intent(this, TreinoActivity::class.java)
// se título for nulo, utilizar nome no app
            val titulo = mensagemRemota?.notification?.title?: getString(R.string.app_name)
            var mensagem = mensagemRemota?.notification?.body!!
            NotificationUtil.create(this, 1, intent, titulo, mensagem)
        }

         fun showNotification(mensagemRemota: RemoteMessage) {
// Intent para abrir quando clicar na notificação
            val intent = Intent(this, TreinoActivity::class.java)
// se título for nulo, utilizar nome no app
            val titulo = mensagemRemota?.notification?.title?: getString(R.string.app_name)
            var mensagem = mensagemRemota?.notification?.body!!
// verificar se existem dados enviados no push
            if(mensagemRemota?.data.isNotEmpty()) {
                val treinoId = mensagemRemota.data.get("treinoId")
                if (treinoId != null) {
                    mensagem = treinoId
                }
            }

            NotificationUtil.create(this, 1, intent, titulo, mensagem)
        }

    }
}