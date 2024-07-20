package com.example.module.found.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module.found.adapter.ClassifyAdapter
import com.example.module.found.adapter.SpecialPreviewAdapter
import com.example.module.found.bean.ClassifyBean
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.FragmentFoundBinding
import com.example.module.found.ui.SpecialAllActivity
import com.example.module.found.viewmodel.ClassifyViewModel
import com.example.module.found.viewmodel.SpecialViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FoundFragment : Fragment() {
    private var mbinding: FragmentFoundBinding? = null

    private lateinit var vmClassify: ClassifyViewModel
    private lateinit var vmSpecial: SpecialViewModel

    private lateinit var classifyList: List<ClassifyBean>
    private lateinit var specialList: List<SpecialDetailBean>

    //private var deliver: DeliverData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewModel
        vmClassify = ViewModelProvider(requireActivity())[ClassifyViewModel::class.java]
        vmSpecial = ViewModelProvider(requireActivity())[SpecialViewModel::class.java]
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
        val specialManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mbinding!!.rvSpecialPreview.layoutManager = specialManager

        getData()

        mbinding?.tvGotoSpecialAll?.setOnClickListener {

            Log.d("specialList", "onViewCreated: $specialList")

            if (specialList.isNotEmpty()) {
                startActivity(Intent(requireActivity(), SpecialAllActivity::class.java))
            }

        }

    }
    private fun getData() {

        lifecycleScope.launch {

            vmSpecial.getSpecialAllData()

            vmSpecial.specialAllStateFlow.collectLatest {
                if (it != null) {
                    specialList = it
                    mbinding!!.rvSpecialPreview.adapter = SpecialPreviewAdapter(it)
                   // deliver?.deliverSpecialData(specialList)
                }
            }
        }

        //请求分类数据
        lifecycleScope.launch {
            // 获取数据
            vmClassify.getClassifyData()
            // 观察数据变化
            vmClassify.classifyStateFlow.collectLatest {
                it?.let {
                    classifyList = it
                    //分类
                    val classifyManager = GridLayoutManager(activity, 3)
                    mbinding!!.rvClassify.layoutManager = classifyManager
                    mbinding!!.rvClassify.adapter = ClassifyAdapter(classifyList)
                }
            }
        }
    }
    interface DeliverData {
        fun deliverSpecialData(specialList: List<SpecialDetailBean>)
    }
}