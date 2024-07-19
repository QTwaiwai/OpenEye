package com.example.module.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.module.home.ViewModel.DailyViewModel
import com.example.module.home.adapter.DailyBannerAdapter
import com.example.module.home.adapter.DailyRvAdapter
import com.example.module.home.databinding.FgHomeDailyBinding
import com.example.module.home.helper.BannerHelper
import java.util.Timer
import java.util.TimerTask

/**
 * description : RecommendViewModel
 * author : QTwawa
 * date : 2024/7/17 15:19
 */

class DailyFragment : Fragment() {
    private val mBinding: FgHomeDailyBinding by lazy {
        FgHomeDailyBinding.inflate(layoutInflater)
    }
    private val mDailyViewModel: DailyViewModel by lazy {
        ViewModelProvider(this)[DailyViewModel::class.java]
    }
    private val mRvAdapter: DailyRvAdapter by lazy {
        DailyRvAdapter(this)
    }
    private val bannerHelper by lazy {
        BannerHelper()
    }
    private var url: String = "" //Rv下一个Url
    private var count: Int = 0
    private lateinit var timerTask: TimerTask
    private var time = Timer()
    private var isDown = false
    private var isStart = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRvView()
        initBanner()
        onScroll()

    }

    private fun initBanner() {
        mDailyViewModel.dailyVpData.observe(viewLifecycleOwner) {
            val list=it
            mRvAdapter.onInitBanner {
                it.submitBannerList(list)
                bannerHelper.initBanner(it)
            }
        }
    }

    private fun initRvView() {
        mDailyViewModel.dailyRvData.observe(viewLifecycleOwner) {
            val list = it.filter { element ->
                element.type != "textCard"
            }
            mRvAdapter.submitList(list)
            count += list.size
        }
        mDailyViewModel.url.observe(viewLifecycleOwner) {
            url = it.replace("http", "https")
        }
        mBinding.rvHomeDaily.apply {
            adapter = mRvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onScroll() {
        mBinding.rvHomeDaily.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                        mDailyViewModel.getNextDailyRvData(url)
                    }
                }
            }
        })
    }
}