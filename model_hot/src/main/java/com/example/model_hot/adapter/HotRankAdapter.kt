package com.example.model_hot.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lib.base.timeConversion
import com.example.model_hot.R
import com.example.model_hot.bean.Item
import com.example.model_hot.databinding.ItemHotBinding
import com.example.module_video.ui.VideoActivity

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/25 15:23
 */
class HotRankAdapter :
    ListAdapter<Item, HotRankAdapter.HotRankViewHolder>(object :
        DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }) {
    inner class HotRankViewHolder(binding: ItemHotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgIcon: ImageView = binding.imgHotIcon
        val tvAuthor: TextView = binding.tvHotAuthor

        val tvTitle: TextView = binding.tvHotTitle
        val tvTag: TextView = binding.tvHotTag
        val tvTime: TextView = binding.tvHotTime
        val imgVideo: ImageView = binding.imgHotVideo
        val imgShare: ImageView = binding.imgHotShare

        init {
            initListener()
        }

        private fun initListener() {
            imgVideo.setOnClickListener {
                val data = getItem(bindingAdapterPosition)

                val intent: Intent = Intent(itemView.context, VideoActivity::class.java).apply {
                    putExtra("title", data.data.title)
                    putExtra("author", data.data.author.name)
                    putExtra("description", data.data.description)
                    putExtra("likes", data.data.consumption.collectionCount)
                    putExtra("tag", data.data.category)
                    putExtra("share", data.data.consumption.shareCount)
                    putExtra("star", data.data.consumption.realCollectionCount)
                    putExtra("url", data.data.playUrl.replace("http", "https"))
                    putExtra("id", data.data.id)
                }
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    imgVideo,
                    imgVideo.transitionName
                )

                itemView.context.startActivity(intent, options.toBundle())

            }

            imgShare.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotRankViewHolder {
        val binding =
            ItemHotBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HotRankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotRankViewHolder, position: Int) {
        val item = getItem(position)

        holder.tvTitle.text = item.data.title
        holder.tvAuthor.text = item.data.author.name
        holder.tvTime.text = item.data.duration.timeConversion()
        holder.tvTag.text = buildString {
                append("#")
                append(item.data.category)
            }

        val imgIconUrl = item.data.author.icon.replace("http://", "https://")
        Glide.with(holder.itemView)
            .load(imgIconUrl)
            .into(holder.imgIcon)

        val imgVideoUrl = item.data.cover.detail.replace("http://", "https://")
        Glide.with(holder.itemView)
            .load(imgVideoUrl)
            .into(holder.imgVideo)
    }
}