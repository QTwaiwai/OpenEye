package com.example.module.found.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.ItemSpecialAllBinding
import com.example.module.found.databinding.ItemSpecialPreviewBinding
import com.example.module.found.ui.SpecialDetailActivity

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:54
 */
class SpecialAllAdapter(private val specialBeanList: List<SpecialDetailBean>) :
    RecyclerView.Adapter<SpecialAllAdapter.ViewHolder>() {

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
                val special = specialBeanList[bindingAdapterPosition]

                SpecialDetailActivity.actionStart(
                    itemView.context,
                    special.id.toString(),
                    special.headerImage.replace("http://", "https://")
                )

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSpecialAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = specialBeanList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val special = specialBeanList[position]

        val imgPreviewUrl: String = special.headerImage.replace("http://", "https://")
        Glide.with(holder.itemView.context)
            .load(imgPreviewUrl)
            .into(holder.imgSpecialAll)
        holder.tvSpecialAllTitle.text = special.brief
        holder.tvSpecialAllDesc.text = special.text
    }
}