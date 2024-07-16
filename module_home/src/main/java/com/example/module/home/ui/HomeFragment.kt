package com.example.module.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.module.home.adapter.HomeAdapter
import com.example.module.home.databinding.FgHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author: QT
 * @date: 2024/7/16
 * description:首页的fragment
 */

class HomeFragment: Fragment() {
    private val mBinding: FgHomeBinding by lazy {
        FgHomeBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mBinding.vp2HomeContent.isSaveEnabled = false
        val fragments = ArrayList<Fragment>()
        fragments.add(DailyFragment())
        fragments.add(RecommendFragment())
        mBinding.vp2HomeContent.adapter = HomeAdapter(fragments,childFragmentManager,lifecycle)
        TabLayoutMediator(mBinding.tabHome,mBinding.vp2HomeContent) {tab,position->
            tab.text = when(position){
                0->"日报"
                1->"推荐"
                else -> error("迷路了QAQ")
            }
        }.attach()
    }
}

