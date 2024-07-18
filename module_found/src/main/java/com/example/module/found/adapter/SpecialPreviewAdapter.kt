package com.example.module.found.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.ItemSpecialPreviewBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:54
 */
class SpecialPreviewAdapter(private val special: SpecialDetailBean) :
    RecyclerView.Adapter<SpecialPreviewAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemSpecialPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgSpecial: ImageView = binding.imgSpecialPreview
        val tvSpecialTitle: TextView = binding.tvSpecialPreviewTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSpecialPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imgPreviewUrl: String = special.headerImage.replace("http://", "https://")
        Glide.with(holder.itemView.context)
            .load(imgPreviewUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imgSpecial)
        holder.tvSpecialTitle.text = special.brief
    }
}