package com.example.module.community.net.loadstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.module.community.R
import com.example.module.community.databinding.LayoutLoadStateBinding

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/26 22:52
 */
class LoadStateViewHolder(
    parent: ViewGroup,
    //retry()便于点击重试后，在adapter中做重试逻辑
    var retry: () -> Unit
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_load_state, parent, false)
    ) {
    var binding: LayoutLoadStateBinding = LayoutLoadStateBinding.bind(itemView)

    fun bindState(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvRetry.visibility = View.VISIBLE
            binding.tvRetry.setOnClickListener {
                retry()
            }
        } else if (loadState is LoadState.Loading) {
            binding.llLoading.visibility = View.VISIBLE
        }

    }
}