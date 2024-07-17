package com.example.module.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.module.home.adapter.RecommendAdapter
import com.example.module.home.databinding.FgHomeRecommendBinding
import com.example.module.home.ViewModel.RecommendViewModel

/**
 * description : RecommendFragment
 * author : QTwawa
 * date : 2024/7/16 17:59
 */
class RecommendFragment: Fragment() {
    private val mBinding: FgHomeRecommendBinding by lazy {
        FgHomeRecommendBinding.inflate(layoutInflater)
    }
    private val mRecommendViewModel: RecommendViewModel by lazy {
        ViewModelProvider(this)[RecommendViewModel::class.java]
    }
    private lateinit var mAdapter: RecommendAdapter
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
        mAdapter= RecommendAdapter(this)
        mBinding.rvHomeRecommend.apply {
            adapter=mAdapter
            layoutManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        mRecommendViewModel.recommendData.observe(viewLifecycleOwner){
            val list = it.filter {element->
                element.type != "horizontalScrollCard"
            }
            mAdapter.submitList(list)
            count+=list.size
        }
        mRecommendViewModel.url.observe(viewLifecycleOwner){
            url=it.replace("http","https")
        }
    }
    private fun onScroll(){
        mBinding.rvHomeRecommend.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager?
                val adapter = recyclerView.adapter

                if (layoutManager != null && adapter != null) {
                    // 获取最后一个可见item的位置
                    val lastVisibleItemPositions =
                        layoutManager.findLastVisibleItemPositions(null)
                    val lastVisibleItemPosition: Int =
                        getLastVisibleItem(lastVisibleItemPositions)

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

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (position in lastVisibleItemPositions) {
            if (position > maxSize) {
                maxSize = position
            }
        }
        return maxSize
    }

    fun updataRecommendData(){
        mRecommendViewModel.getNextRecommend(url)
        mRecommendViewModel.url.observe(viewLifecycleOwner) {
            url=it.replace("http","https")

        }
        mRecommendViewModel.recommendData.observe(viewLifecycleOwner){
            val list = it.filter {element->
                element.type != "horizontalScrollCard"
            }
            mAdapter.submitList(list)
            mAdapter.notifyItemRangeChanged(count,list.size)
            count+=list.size
        }
    }
}
