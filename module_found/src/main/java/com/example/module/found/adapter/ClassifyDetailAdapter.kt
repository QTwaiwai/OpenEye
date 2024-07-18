package com.example.module.found.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.module.found.bean.ClassifyDetail
import com.example.module.found.databinding.ItemClassifyDetailBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/17 15:18
 */
class ClassifyDetailAdapter(private val classifyDetail: ClassifyDetail) :
    RecyclerView.Adapter<ClassifyDetailAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemClassifyDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgAuthorIcon: ImageView = binding.imgIcon
        val tvAuthorName: TextView = binding.tvClassifyAuthorName

        val tvVideoDesc: TextView = binding.tvDetailItem
        val imgVideo: ImageView = binding.imgDetailVideo
        val tvVideoTime: TextView = binding.tvTime

        val tvClassify: TextView = binding.tvClassifyItem
        val tvAgree: TextView = binding.tvAgree
        val imgShare: ImageView = binding.imgDetailShare
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassifyDetailAdapter.ViewHolder {
        val binding =
            ItemClassifyDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassifyDetailAdapter.ViewHolder, position: Int) {
        holder.tvAuthorName.text = classifyDetail.itemList[position].data.header.title

        holder.tvVideoDesc.text = classifyDetail.itemList[position].data.content.data.description
        holder.tvVideoTime.text =
            classifyDetail.itemList[position].data.content.data.duration.toString()
        holder.tvClassify.text =
            buildString {
                append("#")
                append(classifyDetail.itemList[position].data.content.data.category)
            }
        holder.tvAgree.text =
            classifyDetail.itemList[position].data.content.data.consumption.playCount.toString()

        val imgAuthorIconUrl: String =
            classifyDetail.itemList[position].data.header.icon
                .replace("http://", "https://")
        Glide.with(holder.itemView.context)
            .load(imgAuthorIconUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imgAuthorIcon)

        val imgVideoUrl: String =
            classifyDetail.itemList[position].data.content.data.cover.detail
                .replace("http://", "https://")
        Glide.with(holder.itemView.context).load(imgVideoUrl).into(holder.imgVideo)

    }

    override fun getItemCount(): Int = classifyDetail.itemList.size
}