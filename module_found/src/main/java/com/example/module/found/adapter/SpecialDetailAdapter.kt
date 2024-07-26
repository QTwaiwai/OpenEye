package com.example.module.found.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.lib.base.timeConversion
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.ItemSpecialDetialBinding
import com.example.module_video.ui.VideoActivity

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:55
 */
class SpecialDetailAdapter(private val specialDetailBean: SpecialDetailBean) :
    RecyclerView.Adapter<SpecialDetailAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemSpecialDetialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgVideo: ImageView = binding.imgSpecialVideo

        val tvSpecialDetailTitle: TextView = binding.tvSpecialDetailTitle
        val tvSpecialDetailDesc: TextView = binding.tvSpecialDetailDesc
        val tvSpecialDetailTime: TextView = binding.tvSpecialTime

        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {

                val data = specialDetailBean.itemList[bindingAdapterPosition]

                val intent: Intent = Intent(itemView.context, VideoActivity::class.java).apply {
                    putExtra("title", data.data.content.data.title)
                    putExtra("author", data.data.content.data.author.name)
                    putExtra("description", data.data.content.data.description)
                    putExtra("likes", data.data.content.data.consumption.collectionCount)
                    putExtra("tag", data.data.content.data.category)
                    putExtra("share", data.data.content.data.consumption.shareCount)
                    putExtra("star", data.data.content.data.consumption.realCollectionCount)
                    putExtra("url", data.data.content.data.playUrl.replace("http", "https"))
                    putExtra("id", data.data.content.data.id)
                }
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    imgVideo,
                    imgVideo.transitionName
                )

                itemView.context.startActivity(intent, options.toBundle())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSpecialDetialBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = specialDetailBean.itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSpecialDetailTitle.text =
            specialDetailBean.itemList[position].data.content.data.title
        holder.tvSpecialDetailDesc.text =
            specialDetailBean.itemList[position].data.content.data.description
        holder.tvSpecialDetailTime.text =
            specialDetailBean.itemList[position].data.content.data.duration.timeConversion()

        val imgVideoUrl: String =
            specialDetailBean.itemList[position].data.content.data.cover.detail.replace(
                "http://",
                "https://"
            )
        Glide.with(holder.itemView.context)
            .load(imgVideoUrl)
            .into(holder.imgVideo)
    }
}