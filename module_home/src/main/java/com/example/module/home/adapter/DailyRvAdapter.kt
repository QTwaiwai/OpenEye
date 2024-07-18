package com.example.module.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.home.R
import com.example.module.home.bean.DrItem


/**
 * description : DailyRvAdapter
 * author : QTwawa
 * date : 2024/7/17 16:08
 */
class DailyRvAdapter(private val context: Fragment) :
    ListAdapter<DrItem, DailyRvAdapter.DailyRvViewHolder>(object :
        DiffUtil.ItemCallback<DrItem>() {
        override fun areItemsTheSame(oldItem: DrItem, newItem: DrItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DrItem, newItem: DrItem): Boolean {
            return oldItem.data == newItem.data
        }
    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyRvAdapter.DailyRvViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_daily_video, parent, false)
        return DailyRvViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyRvAdapter.DailyRvViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class DailyRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivDailyCover: ImageView = itemView.findViewById(R.id.item_iv_home_daily_video)
        private val ivDailyIcon: ImageView = itemView.findViewById(R.id.item_iv_home_daily_icon)
        private val tvDailyTitle: TextView = itemView.findViewById(R.id.item_tv_home_daily_title)
        private val tvDailyAuthor: TextView = itemView.findViewById(R.id.item_tv_home_daily_author)
        private val tvDailyTime: TextView = itemView.findViewById(R.id.item_tv_home_daily_time)
        private val tvDailyTag: TextView = itemView.findViewById(R.id.item_tv_home_daily_tag)
        @SuppressLint("SetTextI18n")
        fun bind(data: DrItem) {
            Glide.with(itemView.context)
                .load(data.data.content.data.cover.detail?.replace("http", "https"))
                .into(ivDailyCover)
            Glide.with(itemView.context)
                .load(data.data.content.data.author.icon?.replace("http", "https")).circleCrop()
                .into(ivDailyIcon)
            tvDailyTitle.text = data.data.content.data.title
            tvDailyAuthor.text = data.data.content.data.author.name
            tvDailyTag.text = "#" + data.data.content.data.category
            val long = data.data.content.data.duration.toLong()
            val minuter = (long / 60).toInt()
            val second = (long % 60).toInt()
            var time: String = if (minuter < 10) "0$minuter" else minuter.toString()
            time += ":"
            time += if (second < 10) "0$second" else second.toString()
            tvDailyTime.text = time
        }
    }
}