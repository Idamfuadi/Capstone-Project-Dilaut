package com.dicoding.idam.dilaut.fish

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Parcelize
data class FishBase (
        var name: String? = null,
        var desciption: String? = null,
        var price_now: String? = null,
        var price_later:String? = null,
        var avatar_url: String? = ""
) : Parcelable{
    constructor(parcel: Parcel):this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    companion object : Parceler<FishBase> {

        override fun FishBase.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(desciption)
            parcel.writeString(price_now)
            parcel.writeString(price_later)
            parcel.writeString(avatar_url)
        }

        override fun create(source: Parcel): FishBase {
            return FishBase(source)
        }

    }
}