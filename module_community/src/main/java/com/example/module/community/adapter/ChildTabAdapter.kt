package com.example.module.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.community.bean.Item
import com.example.module.community.databinding.ItemRvtabChildBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 13:53
 */
class ChildTabAdapter :
    PagingDataAdapter<Item, ChildTabAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }) {

    inner class ViewHolder(binding: ItemRvtabChildBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgChildItem: ImageView = binding.imgChildItem
        val tvChildTitle: TextView = binding.tvChildItemTitle
        val tvChildDesc: TextView = binding.tvChildDesc

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)!!.run {
            holder.tvChildTitle.text = data.title
            holder.tvChildDesc.text = data.description

            val imgUrl = data.icon.replace("http://", "https://")
            Glide.with(holder.itemView)
                .load(imgUrl)
                .into(holder.imgChildItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRvtabChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
}