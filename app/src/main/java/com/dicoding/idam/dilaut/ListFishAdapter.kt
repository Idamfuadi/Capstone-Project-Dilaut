package com.dicoding.idam.dilaut

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListFishAdapter (val listFish: ArrayList<FishBase>) : RecyclerView.Adapter<ListFishAdapter.ListViewHolder>() {

//    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewgroup.context).inflate(R.layout.area_recycler_view,viewgroup,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val fish = listFish[position]

        Glide.with(holder.itemView.context)
                .load(fish.avatar)
                .apply(RequestOptions().override(55, 55))
                .into(holder.imgPhoto)

        holder.tvName.text = fish.name
        holder.tvDetails.text = fish.desciption

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(mContext, DetailAreaActivity ::class.java)
            moveDetail.putExtra(DetailAreaActivity.EXTRA_NAME, fish.name)
            moveDetail.putExtra(DetailAreaActivity.EXTRA_PHOTO, fish.avatar)
            moveDetail.putExtra(DetailAreaActivity.EXTRA_DETAIL, fish.desciption)
            mContext.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int {
        return listFish.size
    }

    inner class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.area_name)
        var tvDetails: TextView = itemView.findViewById(R.id.area_description)
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_user_photo)

    }
    interface OnItemClickCallback {
        fun onItemClicked(data: FishBase)
    }
}