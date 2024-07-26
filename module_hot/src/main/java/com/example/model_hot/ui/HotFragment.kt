package com.example.model_hot.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.model_hot.R
import com.example.model_hot.adapter.HotVpAdapter
import com.example.model_hot.databinding.FragmentHotBinding
import com.example.model_hot.viewmodel.RankViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HotFragment : Fragment() {
    private val mBinding: FragmentHotBinding by lazy {
        FragmentHotBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        mBinding.vp2Hot.isSaveEnabled = false
        val fragments = ArrayList<Fragment>()
        fragments.add(HotMonthFragment("monthly"))
        fragments.add(HotAllFragment("historical"))
        mBinding.vp2Hot.apply {
            adapter = HotVpAdapter(childFragmentManager,lifecycle,fragments)
            setPageTransformer { page, position ->
                when (position) {
                    in -1f..0f -> {
                        page.rotationY = -30 * position
                    }

                    in 0f..1f -> {
                        page.rotationY = -30 * position
                    }
                }
            }
        }

        TabLayoutMediator(mBinding.tabHot, mBinding.vp2Hot) { tab, position ->
            tab.text = when (position) {
                0 -> "月排行"
                1 -> "总排行"
                else -> error("error")
            }
        }.attach()

    }

}