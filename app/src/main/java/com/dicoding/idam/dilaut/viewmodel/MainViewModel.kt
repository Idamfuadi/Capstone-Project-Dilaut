package com.dicoding.idam.dilaut.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.idam.dilaut.FishBase
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    val listUsers = MutableLiveData<ArrayList<FishBase>>()

    fun setUserNames(username: String) {
        val listUser = ArrayList<FishBase>()
        val url = "https://asia-east2-dilaut.cloudfunctions.net/api_predictFishPrices"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("items")
                    for (i in 0 until items.length()) {
                        val item = items.getJSONObject(i)
                        val fishBase = FishBase()
                        fishBase.name = item.getString("name")
                        fishBase.avatar = item.getString("avatar_url")
//                        fishBase.priceNow = item.getString("price_now")
//                        fishBase.priceLater = item.getString("price_later")
                        listUser.add(fishBase)
                    }
                    listUsers.postValue(listUser)
                } catch (e: Exception) {
                    Log.d("Exception main", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable?
            ) {
                Log.d("Failure", error?.message.toString())
            }
        })
    }

    fun getUsers(): LiveData<ArrayList<FishBase>> {
        return listUsers
    }
}