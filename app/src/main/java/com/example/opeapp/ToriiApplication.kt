package com.example.opeapp

import android.app.Application

class ToriiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: ToriiApplication? = null

        fun getInstance(): ToriiApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no manifest")
            }
            return appInstance!!
        }
    }

}