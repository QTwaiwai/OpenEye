package com.example.module.home.adapter

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
import com.example.module.home.bean.RecItem
import com.example.module.home.bean.RecommendData

/**
 * description : RecommendAdapter
 * author : QTwawa
 * date : 2024/7/16 15:40
 */
class RecommendAdapter(private val context: Fragment): ListAdapter<RecItem, RecommendAdapter.RecommendViewHolder>(object :DiffUtil.ItemCallback<RecItem>(){
    override fun areItemsTheSame(oldItem: RecItem, newItem: RecItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecItem, newItem: RecItem): Boolean {
        return oldItem.id == newItem.id
    }
}) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_recommend,parent,false)
        return RecommendViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val recommendData = getItem(position)
        holder.bind(recommendData)
    }

    inner class RecommendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivRecommendCover: ImageView = itemView.findViewById(R.id.item_iv_recommend_cover)
        private val tvRecommendTitle: TextView = itemView.findViewById(R.id.item_tv_recommend_title)
        private val ivRecommendIcon: ImageView = itemView.findViewById(R.id.item_iv_recommend_icon)
        private val tvRecommendAuthor: TextView = itemView.findViewById(R.id.item_tv_recommend_author)
        private val ivRecommendType: ImageView = itemView.findViewById(R.id.item_iv_recommend_type)
        fun bind(data: RecItem){
            Glide.with(itemView.context).load(data.data.content.data.cover.detail.replace("http","https")).into(ivRecommendCover)
            Glide.with(itemView.context).load(data.data.content.data.owner.avatar.replace("http","https")).circleCrop().into(ivRecommendIcon)
            when(data.data.content.type){
                "video"->{
                        ivRecommendType.setImageResource(R.drawable.ic_play)
                }
                else->{
                    ivRecommendType.setImageResource(R.drawable.ic_photo)
                }
            }
            tvRecommendAuthor.text=data.data.content.data.owner.nickname
            tvRecommendTitle.text=data.data.content.data.description
            }
        }
}
