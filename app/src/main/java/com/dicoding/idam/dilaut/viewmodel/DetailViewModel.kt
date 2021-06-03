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

class DetailViewModel : ViewModel() {
    private val detailUser = MutableLiveData<FishBase>()

    fun setDetailUser(users: String) {
        val url = "https://asia-east2-dilaut.cloudfunctions.net/api_predictFishPrices"
        val asyncClient = AsyncHttpClient()
        asyncClient.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d("ViewModel", result)
                try {
                    val responObjects = JSONObject(result)
                    val fishBase = FishBase()
                    fishBase.name = responObjects.getString("name")
                    fishBase.avatar = responObjects.getString("avatar_url")
                    fishBase.priceNow = responObjects.getString("price_now")
                    fishBase.priceLater = responObjects.getString("price_later")
                    detailUser.postValue(fishBase)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }
        })
    }
    fun getDetailUser(): LiveData<FishBase> {
        return detailUser
    }
}