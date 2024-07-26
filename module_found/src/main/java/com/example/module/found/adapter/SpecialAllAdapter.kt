package com.example.module.found.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.ItemSpecialAllBinding
import com.example.module.found.ui.activity.SpecialDetailActivity

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:54
 */
class SpecialAllAdapter :
    ListAdapter<SpecialDetailBean, SpecialAllAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<SpecialDetailBean>() {
        override fun areItemsTheSame(
            oldItem: SpecialDetailBean,
            newItem: SpecialDetailBean
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SpecialDetailBean,
            newItem: SpecialDetailBean
        ): Boolean {
            return oldItem == newItem
        }
    }) {


    inner class ViewHolder(binding: ItemSpecialAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgSpecialAll: ImageView = binding.imgSpecialAll
        val tvSpecialAllTitle: TextView = binding.tvSpecialAllTitle
        val tvSpecialAllDesc: TextView = binding.tvSpecialAllDesc

        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {
                val special = getItem(bindingAdapterPosition)

                SpecialDetailActivity.actionStart(
                    itemView.context,
                    special.id.toString(),
                    special.headerImage.replace("http://", "https://"),
                    imgSpecialAll
                )

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialAllAdapter.ViewHolder {
        val binding =
            ItemSpecialAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecialAllAdapter.ViewHolder, position: Int) {
        val special = getItem(position)

        val imgPreviewUrl: String = special.headerImage.replace("http://", "https://")
        Glide.with(holder.itemView.context)
            .load(imgPreviewUrl)
            .into(holder.imgSpecialAll)
        holder.imgSpecialAll.transitionName = "transformation$position"

        holder.tvSpecialAllTitle.text = special.brief
        holder.tvSpecialAllDesc.text = special.text
    }

}