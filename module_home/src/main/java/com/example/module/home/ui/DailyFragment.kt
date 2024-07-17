package com.example.module.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.module.home.ViewModel.DailyViewModel
import com.example.module.home.adapter.DailyRvAdapter
import com.example.module.home.databinding.FgHomeDailyBinding

/**
 * description : RecommendViewModel
 * author : QTwawa
 * date : 2024/7/17 15:19
 */

class DailyFragment: Fragment() {
    private val mBinding: FgHomeDailyBinding by lazy {
        FgHomeDailyBinding.inflate(layoutInflater)
    }
    private val mDailyViewModel: DailyViewModel by lazy {
        ViewModelProvider(this)[DailyViewModel::class.java]
    }
    private lateinit var mAdapter: DailyRvAdapter
    private var url:String=""
    private var count:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onScroll()
    }

    private fun initView() {
        mAdapter= DailyRvAdapter(this)
        mDailyViewModel.dailyRvData.observe(viewLifecycleOwner){
            val list = it.filter {element->
                element.type != "textCard"
            }
            mAdapter.submitList(list)
            count+=list.size
        }
        mDailyViewModel.url.observe(viewLifecycleOwner){
            url=it.replace("http","https")
        }
        mBinding.rvHomeDaily.apply {
            adapter=mAdapter
            layoutManager=LinearLayoutManager(context)
        }
    }
    private fun onScroll() {
        mBinding.rvHomeDaily.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val adapter = recyclerView.adapter

                if (layoutManager != null && adapter != null) {
                    // 获取最后一个可见item的位置
                    val lastVisibleItemPosition =
                        layoutManager.findLastVisibleItemPosition()

                    // 获取可见item的数量
                    val visibleItemCount = recyclerView.childCount

                    // 获取Adapter的item总数
                    val totalItemCount = adapter.itemCount

                    // 判断是否到达最后一个元素
                    if (lastVisibleItemPosition + visibleItemCount >= totalItemCount) {
                        updataRecommendData()
                    }
                }
            }
        })
    }

    fun updataRecommendData(){
        mDailyViewModel.getNextDailyRvData(url)
        mDailyViewModel.url.observe(viewLifecycleOwner) {
            url=it.replace("http","https")

        }
        mDailyViewModel.dailyRvData.observe(viewLifecycleOwner){
            val list = it.filter {element->
                element.type != "textCard"
            }
            mAdapter.submitList(list)
            mAdapter.notifyItemRangeChanged(count,list.size)
            count+=list.size
        }
    }
}