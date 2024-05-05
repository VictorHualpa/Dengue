package com.minsa.dengue.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.minsa.dengue.R
import com.minsa.dengue.ui.login.LogearActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME: Long  = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val handler = Handler()
        handler.postDelayed(
            {
                mostrarIniciar();
            },
            SPLASH_TIME
        )
    }

    private fun mostrarIniciar() {
        val sp = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val codusuario = sp.getString("codusuario","0")
        startActivity(Intent(this, LogearActivity::class.java))
        finish()
    }

}