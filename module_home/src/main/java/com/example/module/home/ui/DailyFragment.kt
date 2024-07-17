package com.example.module.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.module.home.adapter.RecommendAdapter
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

    }
}