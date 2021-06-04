package com.dicoding.idam.dilaut.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.idam.dilaut.databinding.AreaRecyclerViewBinding
import com.dicoding.idam.dilaut.fish.FishBase

class Adapter : RecyclerView.Adapter<Adapter.FishViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val list = ArrayList<FishBase>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setFist(FishBases: ArrayList<FishBase>){
        list.clear()
        list.addAll(FishBases)
        notifyDataSetChanged()
    }

    inner class FishViewHolder(private val binding: AreaRecyclerViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(fishBase: FishBase){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(fishBase)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(fishBase.avatar_url)
                    .into(ivUserPhoto)
                areaName.text= fishBase.name

                items.setOnClickListener{onItemClickCallback?.onItemClicked(fishBase)}

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishViewHolder {
        val view = AreaRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return FishViewHolder(view)
    }

    override fun onBindViewHolder(holder: FishViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: FishBase)
    }
}