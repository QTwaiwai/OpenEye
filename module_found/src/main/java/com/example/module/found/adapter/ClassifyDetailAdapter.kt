package com.example.module.found.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.postponeEnterTransition
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lib.base.timeConversion
import com.example.module.found.MyImage
import com.example.module.found.R
import com.example.module.found.bean.ClassifyDetail
import com.example.module.found.databinding.ItemClassifyDetailBinding
import com.example.module.found.ui.SpecialDetailActivity

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

        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {
                val data = classifyDetail.itemList[bindingAdapterPosition]

                ARouter.getInstance().build("/video/VideoActivity")
                    .withString("title", data.data.content.data.title)
                    .withString("author", data.data.content.data.author.name)
                    .withString("description", data.data.content.data.description)
                    .withInt("likes", data.data.content.data.consumption.collectionCount)
                    .withString("tag", data.data.content.data.category)
                    .withInt("share", data.data.content.data.consumption.shareCount)
                    .withInt("star", data.data.content.data.consumption.realCollectionCount)
                    .withString("url", data.data.content.data.playUrl.replace("http", "https"))
                    .withInt("id", data.data.content.data.id)
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation(itemView.context as Activity)


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
        holder.tvAuthorName.text = classifyDetail.itemList[position].data.header.title

        holder.tvVideoDesc.text = classifyDetail.itemList[position].data.content.data.description
        holder.tvVideoTime.text =
            classifyDetail.itemList[position].data.content.data.duration.timeConversion()
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