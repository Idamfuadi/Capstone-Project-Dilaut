package com.dicoding.idam.dilaut.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.idam.dilaut.detail.DetailAreaActivity
import com.dicoding.idam.dilaut.adapter.Adapter
import com.dicoding.idam.dilaut.databinding.ActivityMainBinding
import com.dicoding.idam.dilaut.fish.FishBase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainView: MainViewModel
    private lateinit var adapter: Adapter

    private fun searchFish(){
        binding.apply {
            val name = etQuery.text.toString()
            if (name.isEmpty()) return
            showLoading(true)
            mainView.setSearchFish(name)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = Adapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : Adapter.OnItemClickCallback{
            override fun onItemClicked(data: FishBase) {
                val intent = Intent(this@MainActivity, DetailAreaActivity::class.java)
                intent.putExtra(DetailAreaActivity.EXTRA_NAME, data)
                startActivity(intent)


            }
        })

        mainView = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        mainView.setSearchFish("i")

        binding.apply {
            rvAreas.layoutManager=LinearLayoutManager(this@MainActivity)
            rvAreas.setHasFixedSize(true)
            rvAreas.adapter = adapter

            btnSearch.setOnClickListener{searchFish()}
            etQuery.setOnKeyListener{v, keyCode, event ->

                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchFish()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        mainView.getLoadingStatus().observe(this,{
            showLoading(it)
        })
        mainView.getSearchFish().observe(this,{
            if(it!=null){
                adapter.setFist(it)
                showLoading(false)
            }
        })


        //showRecyclerList()
    }

//    private fun showRecyclerList() {
//        rvFishBase.layoutManager = LinearLayoutManager(this)
//        val listFishAdapter = ListFishAdapter(list)
//        rvFishBase.adapter = listFishAdapter
//    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }


}
