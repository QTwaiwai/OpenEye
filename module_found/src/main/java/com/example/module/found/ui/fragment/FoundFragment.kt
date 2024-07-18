package com.example.module.found.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.module.found.adapter.ClassifyAdapter
import com.example.module.found.bean.ClassifyBean
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.FragmentFoundBinding
import com.example.module.found.viewmodel.ClassifyViewModel
import com.example.module.found.viewmodel.SpecialViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FoundFragment : Fragment() {
    private var mbinding: FragmentFoundBinding? = null

    private lateinit var vmClassify: ClassifyViewModel
    private lateinit var vmSpecial: SpecialViewModel

    private lateinit var classifyList: List<ClassifyBean>
    private lateinit var special: SpecialDetailBean
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
        getData()
    }

    private fun getData() {
        //请求专题数据
        lifecycleScope.launch {
            vmSpecial.getSpecialData()
            vmSpecial.specialStateFlow.collectLatest {
                it?.let {
                    special = it
                    Log.d("specialList", "getData: $it")
                    //专题
                    /*val specialManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                    mbinding!!.rvSpecialPreview.layoutManager = specialManager
                    mbinding!!.rvSpecialPreview.adapter = SpecialPreviewAdapter(special)*/
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

    private fun initRv() {



    }
}