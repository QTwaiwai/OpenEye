package com.example.module.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.module.home.adapter.HomeAdapter
import com.example.module.home.databinding.ActivityHomeBinding
import com.example.module.home.databinding.FgHomeBinding
import com.example.module.home.util.TabTransformer
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author: QT
 * @date: 2024/7/16
 * description:首页的fragment
 */

class HomeFragment : Fragment() {
    private var _binding: FgHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FgHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.vp2HomeContent.isSaveEnabled = false
        val fragments = ArrayList<Fragment>()
        fragments.add(DailyFragment())
        fragments.add(RecommendFragment())
        binding.vp2HomeContent.adapter = HomeAdapter(fragments, childFragmentManager, lifecycle)
        binding.vp2HomeContent.setPageTransformer(TabTransformer())
        TabLayoutMediator(binding.tabHome, binding.vp2HomeContent) { tab, position ->
            tab.text = when (position) {
                0 -> "日报"
                1 -> "推荐"
                else -> error("迷路了QAQ")
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

