package com.example.module.community.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.Item
import com.example.module.community.databinding.ItemVptabBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/22 11:15
 */
class ChildVpAdapter(private val itemList: List<Item>) :
    RecyclerView.Adapter<ChildVpAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemVptabBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}