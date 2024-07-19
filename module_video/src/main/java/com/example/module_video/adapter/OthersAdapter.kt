package com.example.module_video.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_video.R
import com.example.module_video.bean.Item

/**
 * description : TODO:类的作用
 * author : QTwawa
 * date : 2024/7/18 23:01
 */
class OthersAdapter: ListAdapter<Item, OthersAdapter.OthersViewHolder>(object :
    DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.data == newItem.data
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OthersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_others, parent, false)
        return OthersViewHolder(view)
    }

    override fun onBindViewHolder(holder: OthersViewHolder, position: Int) {
        val data=getItem(position)
        holder.bind(data)
    }

    inner class OthersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivOthersCover: ImageView = itemView.findViewById(R.id.item_iv_cover)
        private val tvOthersTitle: TextView = itemView.findViewById(R.id.item_tv_title)
        private val tvOthersTime: TextView = itemView.findViewById(R.id.item_tv_time)
        fun bind(data: Item){
            Glide.with(itemView.context)
                .load(data.data.cover.detail.replace("http", "https"))
                .into(ivOthersCover)
            tvOthersTitle.text = data.data.title
            val long = data.data.duration.toLong()
            val minuter = (long / 60).toInt()
            val second = (long % 60).toInt()
            var time: String = if (minuter < 10) "0$minuter" else minuter.toString()
            time += ":"
            time += if (second < 10) "0$second" else second.toString()
            tvOthersTime.text = time
        }
    }
}