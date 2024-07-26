package com.example.model_hot.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.model_hot.ui.HotAllFragment
import com.example.model_hot.ui.HotMonthFragment

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/25 15:18
 */
class HotVpAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    fragments: List<Fragment>
) :
    FragmentStateAdapter(fragmentManager,lifecycle) {
    private val fragmentList = fragments

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment =
        /*when (position) {
            0 -> HotMonthFragment("monthly")
            else -> HotAllFragment("historical")
        }*/
        fragmentList[position]
}