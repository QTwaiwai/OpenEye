package com.example.module.community.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.module.community.ChildTabActivity
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.TabListBean
import com.example.module.community.databinding.ItemCommunityBinding
import com.example.module.community.viewmodel.TabViewModel

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:41
 */
class TabAdapter(
    private val tabListBean: TabListBean,
    private val childTab: MutableList<ChildTabBean>
) :
    RecyclerView.Adapter<TabAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemCommunityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvName: TextView = binding.tvCommunityTabName
        val vpChild: ViewPager2 = binding.vpChildTab

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
                        "0",
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
        val item = tabListBean.tabInfo.tabList[position]
        Log.d("ZeqItem", "onBindViewHolder: $item")
        val childItem = childTab[position]
        Log.d("ZeqChildItem", "onBindViewHolder: $childItem")

        holder.tvName.text = item.name

        holder.vpChild.adapter = ChildVpAdapter(childItem.itemList)
    }
}