package com.example.opeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.toolbar.*

open class DebugActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "Torii Gen"
    private val className: String
    get() {
        val s = javaClass.name
        return s.substring(s.lastIndexOf("."))
    }

    var genericLayoutMenu: DrawerLayout? = null
    var genericMenuLateral: NavigationView? = null

    protected fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(
            this,
            genericLayoutMenu,
            toolbar_view,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        )
        genericLayoutMenu?.addDrawerListener(toogle)
        toogle.syncState()
        genericMenuLateral?.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.nav_treinos -> {
                Toast.makeText(this, "Menu Treinos", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_mensagens -> {
                val intent = Intent(this, MensagensActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_pagamentos -> {
                Toast.makeText(this, "Menu pagamentos", Toast.LENGTH_SHORT). show()
            }
            R.id.nav_turmas -> {
                Toast.makeText(this, "Menu turmas", Toast.LENGTH_SHORT). show()
            }
            R.id.nav_config -> {
                Toast.makeText(this, "Menu configurações", Toast.LENGTH_SHORT). show()
            }
        }
        genericLayoutMenu?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$className. onCreate chamado")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$className. onStart chamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "$className. onRestart chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$className. onResume chamado")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$className. onPause chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "$className. onStop chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "$className. onDestroy chamado")
    }


}
