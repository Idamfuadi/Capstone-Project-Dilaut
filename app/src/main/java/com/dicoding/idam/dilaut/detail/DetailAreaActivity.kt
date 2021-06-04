package com.dicoding.idam.dilaut.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.idam.dilaut.R
import com.dicoding.idam.dilaut.databinding.ActivityDetailAreaBinding
import com.dicoding.idam.dilaut.fish.FishBase

class DetailAreaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAreaBinding

    companion object {
        const val EXTRA_NAME = "extra_name"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setTitle(R.string.detail_fish)


        val tName = intent.getParcelableExtra<FishBase>(EXTRA_NAME) as FishBase
        Log.d("<DEBUG>", "Success: ")


        val bundle = Bundle()
        bundle.putString(EXTRA_NAME, tName.toString())
        Log.d("<SUCCESS>", "Success juga kok: " + tName.toString())
        binding.apply {
            tvDetailName.text = tName.name
            priceNowRupiah.text = tName.price_now
            priceLaterRupiahFirst.text = tName.price_now
            priceLaterRupiahSecond.text = tName.price_later
            Glide.with(this@DetailAreaActivity)
                .load(tName.avatar_url)
                .into(ivDetailFish)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}