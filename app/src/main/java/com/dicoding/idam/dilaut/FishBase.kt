package com.dicoding.idam.dilaut

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FishBase (
    var avatar: String? = null, //avatar_url
    var name: String? = null, //name
    var priceNow: String? = null, //priceNow
    var priceLater: String? = null //priceLater
) : Parcelable