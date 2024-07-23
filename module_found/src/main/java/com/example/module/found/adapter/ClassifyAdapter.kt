package com.example.module.found.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.module.found.R
import com.example.module.found.bean.ClassifyBean
import com.example.module.found.ui.ClassifyDetailActivity

class ClassifyAdapter(private val classifyList: List<ClassifyBean>) :
    RecyclerView.Adapter<ClassifyAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val classifyName: TextView = v.findViewById(R.id.tv_classify_item)

        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {
                val item = classifyList[absoluteAdapterPosition]

                ClassifyDetailActivity.startDetail(
                    itemView.context,
                    item.id.toString(),
                    item.description,
                    classifyName
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_classify, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = classifyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val classify = classifyList[position]
        holder.classifyName.text = classify.name
        holder.classifyName.transitionName = "jump$position"
    }
}