package com.dicoding.idam.dilaut.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.idam.dilaut.api.Retrofit
import com.dicoding.idam.dilaut.fish.FishBase
import com.dicoding.idam.dilaut.fish.FishResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val listFish= MutableLiveData<ArrayList<FishBase>>()
    fun setSearchFish(name:String){
        isLoading.postValue(true)
        Retrofit.apiInstance
            .getDataFish(name)
            .enqueue(object : Callback<FishResponse> {
                override fun onResponse(
                    call: Call<FishResponse>,
                    response: Response<FishResponse>
                ) {
                    Log.d("Success","Success")
                    isLoading.postValue(false)
                    if (response.isSuccessful){
                        Log.d("<DEBUG>", "Success: " + response.body()?.items?.size)
                        listFish.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<FishResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                    isLoading.postValue(false)
                }

            })
    }
    val isLoading = MutableLiveData<Boolean>()
    fun getLoadingStatus(): LiveData<Boolean> = isLoading
    fun getSearchFish(): LiveData<ArrayList<FishBase>> {
        return listFish
    }
}