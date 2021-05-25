package com.dicoding.idam.dilaut.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dicoding.idam.dilaut.MainActivity
import com.dicoding.idam.dilaut.R
import com.dicoding.idam.dilaut.databinding.ActivitySplashScreenBinding




class SplashScreen : AppCompatActivity() {
    private lateinit var handler : Handler
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_screen)
        setContentView(binding.root)
        handler= Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3500)
        //val gambergif = ImageView(findViewById(R.id.imgGif))
        binding.apply {
            Glide.with(this@SplashScreen)
                .load(R.drawable.dilaut1)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imgGif)
        }
    }
}