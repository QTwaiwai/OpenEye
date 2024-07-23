package com.example.module.found.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.ItemSpecialPreviewBinding
import com.example.module.found.ui.SpecialDetailActivity

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:54
 */
class SpecialPreviewAdapter(private val SpecialBeanList: List<SpecialDetailBean>) :
    RecyclerView.Adapter<SpecialPreviewAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemSpecialPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgSpecial: ImageView = binding.imgSpecialPreview
        val tvSpecialTitle: TextView = binding.tvSpecialPreviewTitle

        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {
                val special = SpecialBeanList[bindingAdapterPosition]

                SpecialDetailActivity.actionStart(
                    itemView.context,
                    special.id.toString(),
                    special.headerImage.replace("http://", "https://"),
                    imgSpecial
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSpecialPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val special = SpecialBeanList[position]

        val imgPreviewUrl: String = special.headerImage.replace("http://", "https://")
        Glide.with(holder.itemView.context)
            .load(imgPreviewUrl)
            .into(holder.imgSpecial)
        holder.tvSpecialTitle.text = special.brief
    }
}