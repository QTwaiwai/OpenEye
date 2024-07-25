package com.example.module.community

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.base.BaseFragment
import com.example.module.community.adapter.TabAdapter
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.databinding.FragmentCommunityBinding
import com.example.module.community.viewmodel.ChildTabViewModel
import com.example.module.community.viewmodel.TabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {
    private lateinit var vmTab: TabViewModel
    private lateinit var vmChild: ChildTabViewModel
    private var  childTab = mutableListOf<ChildTabBean>()


    private fun getChildBean() {
        viewLifecycleOwner.lifecycleScope.launch {
            vmChild.getChildTabData()

            vmChild.childTabStateFlow.collect {
                it?.let {
                    Log.d("Zeq", "getTabData: $it")
                    childTab.addAll(it)
                    getTabData()
                }
            }
        }
    }

    override fun afterViewCreate() {
        // 初始化 ViewModel
        vmTab = ViewModelProvider(requireActivity())[TabViewModel::class.java]
        vmChild = ViewModelProvider(requireActivity())[ChildTabViewModel::class.java]
        //rvManager
        mbinding.rvCommunityTab.layoutManager = LinearLayoutManager(activity)

        getChildBean()
    }

    private fun getTabData() {
        lifecycleScope.launch {
            vmTab.getTabData()

            vmTab.tabStateFlow.collect {
                if (it != null) {
                    Log.d("Zeq666", "getTabData: $it")
                    mbinding.rvCommunityTab.adapter = TabAdapter(it, childTab)
                }
            }
        }
    }

    override fun getViewBinding(): FragmentCommunityBinding =
        FragmentCommunityBinding.inflate(layoutInflater)
}