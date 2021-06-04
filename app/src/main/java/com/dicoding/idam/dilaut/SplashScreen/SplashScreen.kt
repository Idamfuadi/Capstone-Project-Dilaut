package com.dicoding.idam.dilaut.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import androidx.appcompat.app.AppCompatActivity
import com.dicoding.idam.dilaut.main.MainActivity
import com.dicoding.idam.dilaut.R
import com.dicoding.idam.dilaut.databinding.ActivitySplashScreenBinding




class SplashScreen : AppCompatActivity() {
    private lateinit var handler : Handler
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash_screen)

        handler= Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3500)
    }
}