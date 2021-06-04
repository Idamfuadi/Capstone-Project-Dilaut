package com.dicoding.idam.dilaut

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.idam.dilaut.adapter.ListFishAdapter
import com.dicoding.idam.dilaut.databinding.ActivityMainBinding
import com.dicoding.idam.dilaut.fish.FishBase
import com.dicoding.idam.dilaut.viewmodel.DetailViewModel
import com.dicoding.idam.dilaut.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ListFishAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        binding.rvFish.setHasFixedSize(true)
        showRecyclerList()
        mainViewModel.getUsers().observe(this, { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
                showLoading(false)
            }
        })
    }

    private fun showRecyclerList() {
        binding.rvFish.layoutManager = LinearLayoutManager(this)
        adapter = ListFishAdapter()
        adapter.notifyDataSetChanged()
        binding.rvFish.adapter = adapter
        showLoading(false)

        adapter.setOnItemClickCallback(object : ListFishAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FishBase) {
                showSelectedGithubUser(data)
            }
        })
    }

    private fun showSelectedGithubUser(data: FishBase) {
        val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveIntent.putExtra(DetailActivity.EXTRA_USER, data)
        startActivity(moveIntent)
        detailViewModel = DetailViewModel()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Cari Ikan"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    showLoading(false)
                } else {
                    showLoading(true)
                    mainViewModel.setUserNames(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    showLoading(false)
                } else {
                    showLoading(true)
                    mainViewModel.setUserNames(newText)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

//    private lateinit var rvFishBase: RecyclerView
//    private var list: ArrayList<FishBase> = arrayListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        rvFishBase = findViewById(R.id.rv_areas)
//        rvFishBase.setHasFixedSize(true)
//
//        list.addAll(FishData.listData)
//        showRecyclerList()
//    }
//
//    private fun showRecyclerList() {
//        rvFishBase.layoutManager = LinearLayoutManager(this)
//        val listFishAdapter = ListFishAdapter(list)
//        rvFishBase.adapter = listFishAdapter
//    }


}
