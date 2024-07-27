package com.example.module.community

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.base.BaseFragment
import com.example.lib.base.NetStatus
import com.example.module.community.adapter.ChildTabAdapter
import com.example.module.community.adapter.TabAdapter
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.TabListBean
import com.example.module.community.databinding.FragmentCommunityBinding
import com.example.module.community.viewmodel.ChildTabViewModel
import com.example.module.community.viewmodel.TabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {
    private lateinit var vmTab: TabViewModel
    private lateinit var vmChild: ChildTabViewModel
    private var childTab = mutableListOf<ChildTabBean>()
    private var tabListBean: TabListBean? = null
    private val mAdapter: TabAdapter by lazy {
        tabListBean?.let { TabAdapter(it, childTab) }!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化 ViewModel
        vmTab = ViewModelProvider(requireActivity())[TabViewModel::class.java]
        vmChild = ViewModelProvider(requireActivity())[ChildTabViewModel::class.java]
        //rvManager
        mbinding?.rvCommunityTab?.layoutManager = LinearLayoutManager(activity)

        getChildBean()
        aboutLoad()
    }

    private fun getChildBean() {
        viewLifecycleOwner.lifecycleScope.launch {
            vmChild.getChildTabData()

            vmChild.childTabStateFlow.collect {
                it?.let {
                    childTab.addAll(it)
                    getTabData()
                }
            }
        }
    }

    private fun getTabData() {
        lifecycleScope.launch {
            vmTab.getTabData()

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vmTab.tabStateFlow.collectLatest {
                    if (it != null) {
                        Log.d("Zeq666", "getTabData: $it")
                        mbinding?.rvCommunityTab?.apply {
                            tabListBean = it
                            adapter = mAdapter
                            mbinding!!.tvCommunityEnd.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun aboutLoad() {
        vmTab.loadStatus.observe(viewLifecycleOwner) {
            when (it) {
                NetStatus.LOADING -> {
                    mbinding?.progressBar?.visibility = View.VISIBLE
                }

                NetStatus.SUCCESS -> {
                    mbinding?.progressBar?.visibility = View.GONE
                    Toast.makeText(
                        this@CommunityFragment.requireContext(),
                        "加载成功！",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    mbinding?.progressBar?.visibility = View.GONE
                    Toast.makeText(
                        this@CommunityFragment.requireContext(),
                        "加载失败",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getViewBinding(): FragmentCommunityBinding =
        FragmentCommunityBinding.inflate(layoutInflater)
}