package com.example.module.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.databinding.ItemCommunityBinding
import com.example.module.community.databinding.ItemRvtabChildBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 13:53
 */
class ChildTabAdapter(private val childTabBean: ChildTabBean) :
    ListAdapter<ChildTabBean, ChildTabAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<ChildTabBean>() {
        override fun areItemsTheSame(oldItem: ChildTabBean, newItem: ChildTabBean): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChildTabBean, newItem: ChildTabBean): Boolean {
            return oldItem.count == newItem.count
        }


    }) {
    inner class ViewHolder(binding: ItemRvtabChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgChildItem: ImageView = binding.imgChildItem
        val tvChildTitle: TextView = binding.tvChildItemTitle
        val tvChildDesc: TextView = binding.tvChildDesc

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRvtabChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val childItem = childTabBean.itemList[position]
        holder.tvChildTitle.text = childItem.data.title
        holder.tvChildDesc.text = childItem.data.description

        val imgUrl = childItem.data.icon.replace("http://", "https://")
        Glide.with(holder.itemView.context)
            .load(imgUrl)
            .into(holder.imgChildItem)
    }
}