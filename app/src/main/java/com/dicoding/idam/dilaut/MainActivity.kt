package com.dicoding.idam.dilaut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFishBase: RecyclerView
    private var list: ArrayList<FishBase> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFishBase = findViewById(R.id.rv_areas)
        rvFishBase.setHasFixedSize(true)

        list.addAll(FishData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvFishBase.layoutManager = LinearLayoutManager(this)
        val listFishAdapter = ListFishAdapter(list)
        rvFishBase.adapter = listFishAdapter
    }


}
