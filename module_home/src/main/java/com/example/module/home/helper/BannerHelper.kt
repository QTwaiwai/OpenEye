package com.example.module.home.helper

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.module.home.adapter.DailyBannerAdapter
import com.example.module.home.adapter.DailyRvAdapter
import com.example.module.home.bean.DvItem
import com.example.module.home.util.MyTransformer

/**
 * description : Banner轮播
 * author : QTwawa
 * date : 2024/7/18 20:18
 */
class BannerHelper {
    private lateinit var mViewPager2: ViewPager2
    private lateinit var mData: List<DvItem>
    private val viewPager2Adapter: DailyBannerAdapter by lazy { DailyBannerAdapter() }
    private lateinit var rvAdapter: DailyRvAdapter
    private var isDown:Boolean=false

    fun initBanner(rv:DailyRvAdapter) {
        rvAdapter = rv
        //赋值视图
        mViewPager2 = rv.mViewPager2
        //获取集合
        mData = rv.bannerList
        viewPager2Adapter.submitList(rv.bannerList)
        mViewPager2.adapter = viewPager2Adapter
        mViewPager2.offscreenPageLimit=3
        mViewPager2.setPageTransformer(MyTransformer())
        //开始轮播
        startRun()
//        //触摸事件
//        mViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                isDown=true
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                // 页面正在滑动时触发
//             override fun onPageScrollStateChanged(state: Int) {
//           }
//
//               isDown=false
//                startRun()
//            }
//        })
    }

    private fun startRun() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (isDown){
                    return
                }
                val currentItem = mViewPager2.currentItem
                val nextItem =  if (currentItem == mData.size - 1) 100 else currentItem + 1
                mViewPager2.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 3000)
            }
        }
        handler.post(runnable)
    }
}

