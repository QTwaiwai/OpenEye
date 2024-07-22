package com.example.module.community

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.base.BaseFragment
import com.example.module.community.adapter.TabAdapter
import com.example.module.community.databinding.FragmentCommunityBinding
import com.example.module.community.viewmodel.TabViewModel
import kotlinx.coroutines.launch

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {
    private lateinit var vmTab: TabViewModel

    //private var deliver: Deliver? = null

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        deliver =
            context as? Deliver ?: throw ClassCastException("$context must implement Deliver")

    }*/

    override fun afterViewCreate() {
        // 初始化 ViewModel
        vmTab = ViewModelProvider(requireActivity())[TabViewModel::class.java]

        //rvManager
        mbinding.rvCommunityTab.layoutManager = LinearLayoutManager(activity)

        getTabData()
    }

    private fun getTabData() {
        lifecycleScope.launch {

            vmTab.getTabData()

            vmTab.tabStateFlow.collect {
                if (it != null) {
                    mbinding.rvCommunityTab.adapter = TabAdapter(it)

                    /*for (i in it.tabInfo.tabList) {
                        if (i.id > 0) {
                            vmTab.getChildTabData(i.id.toString())
//                            deliver?.deliverId(i.id.toString())
                        } else
                            vmTab.getChildTabData("0?isRecTab=true")
                        //deliver?.deliverId("0?isRecTab=true")
                    }*/
                    //deliver?.deliverId(id)
                }
            }
        }
    }

    override fun getViewBinding(): FragmentCommunityBinding =
        FragmentCommunityBinding.inflate(layoutInflater)

    /*override fun deliverId(id: String) {
        *//*lifecycleScope.launch {
            vmTab.getChildTabData(id)

            vmTab.childTabStateFlow.collectLatest {
                if (it != null) {

                }
            }
        }*//*
    }
*/
    /*override fun deliverId(id: String) {
        lifecycleScope.launch {
            vmTab.getChildTabData(id)

            vmTab.childTabStateFlow.collectLatest {
                if (it != null) {

                }
            }
        }
    }*/

}
/*
interface Deliver {
    fun deliverId(id: String)
}*/
