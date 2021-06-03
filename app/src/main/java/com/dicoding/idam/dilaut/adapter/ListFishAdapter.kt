package com.dicoding.idam.dilaut.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.idam.dilaut.FishBase
import com.dicoding.idam.dilaut.databinding.ItemRowFishBinding

class ListFishAdapter : RecyclerView.Adapter<ListFishAdapter.ListViewHolder>() {
    private val fishData = ArrayList<FishBase>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<FishBase>) {
        fishData.clear()
        fishData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFishBinding.inflate(LayoutInflater.from(viewgroup.context), viewgroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(fishData[position])
    }

    override fun getItemCount(): Int = fishData.size

    inner class ListViewHolder(private val binding: ItemRowFishBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fishBase: FishBase) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(fishBase.avatar)
                    .into(ivUserPhoto)

                tvFishName.text = fishBase.name
//                tvPriceNow.text = fishBase.priceNow//baru
//                tvPriceLater.text = fishBase.priceLater//baru
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(fishBase) }
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FishBase)
    }
}