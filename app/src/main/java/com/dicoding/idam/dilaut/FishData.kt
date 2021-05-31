package com.dicoding.idam.dilaut

object FishData {
    private val fishName = arrayOf(
            "Gurami",
            "Kakap Putih",
            "Kerapu",
            "Lele",
            "Nila",
            "Patin",
            "Mujair",
            "Ikan Mas",
            "Tawes",
            "Udang Vaname")

    private val fishDescription = arrayOf(
            "Ini deskripsi untuk Gurami",
            "Ini deskripsi untuk Kakap Putih",
            "Ini deskripsi untuk Kerapu",
            "Ini deskripsi untuk Lele",
            "Ini deskripsi untuk Nila",
            "Ini deskripsi untuk Patin",
            "Ini deskripsi untuk Mujair",
            "Ini deskripsi untuk Ikan Mas",
            "Ini deskripsi untuk Tawes",
            "Ini deskripsi untuk Udang Vaname")

    private val fishImage = arrayOf(
            R.drawable.gurami,
            R.drawable.kakap_putih,
            R.drawable.ikan_kerapu,
            R.drawable.lele,
            R.drawable.nila,
            R.drawable.patin,
            R.drawable.mujair,
            R.drawable.ikan_mas,
            R.drawable.tawes,
            R.drawable.udang_vaname)

    val listData: ArrayList<FishBase>
        get() {
            val list = arrayListOf<FishBase>()
            for (position in fishName.indices) {
                val fishBase = FishBase()
                fishBase.name = fishName[position]
                fishBase.desciption = fishDescription[position]
                fishBase.avatar = fishImage[position]
                list.add(fishBase)
            }
            return list
        }
}