package com.example.module.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module.home.ViewModel.DailyViewModel
import com.example.module.home.adapter.DailyRvAdapter
import com.example.module.home.databinding.FgHomeDailyBinding
import com.example.module.home.helper.BannerHelper

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            val list = it
            mRvAdapter.onInitBanner { adapter ->
                adapter.submitBannerList(list)
                bannerHelper.initBanner(adapter)
            }
        }
    }

    private fun initRvView() {
        mDailyViewModel.dailyRvData.observe(viewLifecycleOwner) {
            val list = it.filter { element ->
                element.type != "textCard"
            }
            mRvAdapter.submitList(list)
        }
        mDailyViewModel.url.observe(viewLifecycleOwner) {
            url = it.replace("http", "https")
        }
        mBinding.rvHomeDaily.apply {
            adapter = mRvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onScroll() {
        //滑到下面加载更多
        mBinding.rvHomeDaily.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    mDailyViewModel.getNextDailyRvData(url)
                }
            }
        })
        //判断是否阻拦横向滑动
        val recyclerViewTouchListener = View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_MOVE||event.action == MotionEvent.ACTION_DOWN) {
                val childView = mBinding.rvHomeDaily.findChildViewUnder(event.x, event.y)
                if (childView != null) {
                    val position = mBinding.rvHomeDaily.getChildAdapterPosition(childView)
                    mBinding.rvHomeDaily.isScrollEnabled = position > 2
                }
            }
            false
        }
        mBinding.rvHomeDaily.setOnTouchListener(recyclerViewTouchListener)
    }
}