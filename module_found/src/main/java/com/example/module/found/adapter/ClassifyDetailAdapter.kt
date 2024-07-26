package com.example.module.found.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lib.base.timeConversion
import com.example.module.found.bean.ClassifyDetail
import com.example.module.found.bean.Item
import com.example.module.found.databinding.ItemClassifyDetailBinding
import com.example.module_video.ui.VideoActivity

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/17 15:18
 */
class ClassifyDetailAdapter :
    PagingDataAdapter<Item, ClassifyDetailAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }) {

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

        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {
                val data = getItem(bindingAdapterPosition)!!

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

            imgShare.setOnClickListener {
                val data = getItem(bindingAdapterPosition)
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "我在开眼发现一个很棒的视频，快来看看吧！\n${
                        data!!.data.content.data.playUrl.replace(
                            "http",
                            "https"
                        )
                    }"
                )
                itemView.context.startActivity(Intent.createChooser(intent, "分享到"))
            }
        }
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
        getItem(position)!!.run {
            holder.tvAuthorName.text = data.header.title

            holder.tvVideoDesc.text = data.content.data.description
            holder.tvVideoTime.text =
                data.content.data.duration.timeConversion()
            holder.tvClassify.text =
                buildString {
                    append("#")
                    append(data.content.data.category)
                }
            holder.tvAgree.text = data.content.data.consumption.playCount.toString()

            val imgAuthorIconUrl: String = data.header.icon
                    .replace("http://", "https://")
            Glide.with(holder.itemView.context)
                .load(imgAuthorIconUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imgAuthorIcon)

            val imgVideoUrl: String = data.content.data.cover.detail
                    .replace("http://", "https://")
            Glide.with(holder.itemView.context).load(imgVideoUrl).into(holder.imgVideo)
        }


    }
}