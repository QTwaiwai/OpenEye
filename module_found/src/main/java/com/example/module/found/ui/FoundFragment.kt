package com.example.module.found.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.base.BaseFragment
import com.example.module.found.adapter.ClassifyAdapter
import com.example.module.found.bean.Classify
import com.example.module.found.databinding.FragmentFoundBinding
import com.example.module.found.viewmodel.ClassifyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch


class FoundFragment : Fragment() {
    private var mbinding: FragmentFoundBinding? = null
    private lateinit var mViewModel: ClassifyViewModel
    private lateinit var classifyList: List<Classify>

    private lateinit var classifyAdapter: ClassifyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewModel
        mViewModel = ViewModelProvider(requireActivity())[ClassifyViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mbinding = FragmentFoundBinding.inflate(inflater, container, false)

        return mbinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            // 获取数据
            mViewModel.getClassifyData()
            // 观察数据变化
            mViewModel.classifyStateFlow.collectLatest {
                it?.let {
                    classifyList = it
                    initRv()
                }
            }
        }
    }

    private fun initRv() {
        val layoutManager = GridLayoutManager(activity, 3)
        classifyAdapter=ClassifyAdapter(classifyList)
        mbinding!!.rvClassify.layoutManager = layoutManager
        mbinding!!.rvClassify.adapter = classifyAdapter
    }
}