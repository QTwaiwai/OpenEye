package com.example.module.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.module.community.ChildTabActivity
import com.example.module.community.bean.TabListBean
import com.example.module.community.databinding.ItemCommunityBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:41
 */
class TabAdapter(private val tabListBean: TabListBean) :
    RecyclerView.Adapter<TabAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemCommunityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvName: TextView = binding.tvCommunityTabName

        init {
            initListener()
        }

        private fun initListener() {

            tvName.setOnClickListener {
                val childTab = tabListBean.tabInfo.tabList[bindingAdapterPosition]
                val id: Int = childTab.id

                if (id > 0) {
                    ChildTabActivity.startChild(
                        itemView.context,
                        id.toString(),
                        childTab.name
                    )
                } else
                    ChildTabActivity.startChild(
                        itemView.context,
                        "0?isRecTab=true",
                        childTab.name
                    )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = tabListBean.tabInfo.tabList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = tabListBean.tabInfo.tabList[position].name
    }
}