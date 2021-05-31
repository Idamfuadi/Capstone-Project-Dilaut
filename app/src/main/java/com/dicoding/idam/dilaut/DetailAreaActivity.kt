package com.dicoding.idam.dilaut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailAreaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_area)
        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.detail_fish)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val tvSetName: TextView = findViewById(R.id.tv_detail_name)
        val imgSetPhoto: ImageView = findViewById(R.id.iv_detail_fish)
        val tvSetDetail: TextView = findViewById(R.id.tv_detail_description)

        val tName  = intent.getStringExtra(EXTRA_NAME)
        val tImg = intent.getIntExtra(EXTRA_PHOTO,0)
        val tIden = intent.getStringExtra(EXTRA_DETAIL)


        tvSetName.text = tName
        Glide.with(this)
                .load(tImg)
                .apply(RequestOptions())
                .into(imgSetPhoto)
        tvSetDetail.text = tIden
    }

    companion object {
        const val EXTRA_NAME = "extra name"
        const val EXTRA_PHOTO = "extra photo"
        const val EXTRA_DETAIL = "extra detail"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}