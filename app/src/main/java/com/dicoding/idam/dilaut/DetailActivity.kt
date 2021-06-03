package com.dicoding.idam.dilaut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.idam.dilaut.databinding.ActivityDetailBinding
import com.dicoding.idam.dilaut.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionbar = supportActionBar
        actionbar?.title = "Informasi dan Prediksi"
        actionbar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = DetailViewModel()
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java)
        val fishData = intent.getParcelableExtra<FishBase>(EXTRA_USER) as FishBase

        detailViewModel.getDetailUser().observe(this, {
            showLoading(true)
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(it.avatar)
                    .into(ivDetailFish)

                tvDetailName.text = if (it.name == null) "Eror Loading" else it.name
                tvPriceNowRupiah.text = if (it.priceNow == null) "Eror Loading" else it.priceNow
                tvPriceLaterRupiah.text = if (it.priceLater == null) "Eror Loading" else it.priceLater
            }
            showLoading(false)
        })

//        initTabLayout(userName = String())

//        userData.login?.let {
//            initTabLayout(it)
//            detailViewModel.setDetailUser(it)
//        }

//        fishData.name?.let {
//            initTabLayout(it)
//            detailViewModel.setDetailUser(it)
//        }
    }

//    private fun initTabLayout(userName: String) {
//        val sectionsPagerAdapter = SectionPagerAdapter(this)
//        sectionsPagerAdapter.username = userName
//
//        val viewPager: ViewPager2 = binding.viewPager
//        viewPager.adapter = sectionsPagerAdapter
//
//        val tabs: TabLayout = binding.tabs
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(
//                TAB_TITLES[position])
//        }.attach()
//        supportActionBar?.elevation = 0f
//    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}