package com.example.module.community.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.community.ChildTabActivity
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.Item
import com.example.module.community.databinding.ItemVptabBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/22 11:15
 */
class ChildVpAdapter(private val childItem:List<Item>) :
    RecyclerView.Adapter<ChildVpAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemVptabBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvTitle: TextView = binding.tvChildTabTitle
        val imgChild: ImageView = binding.imgDetailVideo

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemVptabBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = childItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = childItem[position]
        holder.tvTitle.text = item.data.title

        val imgPreviewUrl: String = item.data.icon.replace("http://", "https://")
        Log.d("Zeq", "onBindViewHolder: $imgPreviewUrl")
        Glide.with(holder.itemView.context)
            .load(imgPreviewUrl)
            .into(holder.imgChild)
    }
}