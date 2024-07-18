package com.example.module.found.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module.found.R
import com.example.module.found.bean.Classify
import com.example.module.found.ui.ClassifyDetailActivity

class ClassifyAdapter(private val classifyList: List<Classify>) :
    RecyclerView.Adapter<ClassifyAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val classifyName: TextView = v.findViewById(R.id.tv_classify_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_classify, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
            val item = classifyList[holder.adapterPosition]

            ClassifyDetailActivity.startDetail(
                parent.context,
                item.id.toString(),
                item.name,
                item.description
            )
            Log.d("item.id", "onCreateViewHolder: ${item.id}")
        }
        return holder
    }

    override fun getItemCount(): Int = classifyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val classify = classifyList[position]
        holder.classifyName.text = classify.name
    }
}