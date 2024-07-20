package com.example.module.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.module.home.adapter.RecommendAdapter
import com.example.module.home.databinding.FgHomeRecommendBinding
import com.example.module.home.ViewModel.RecommendViewModel

/**
 * description : RecommendFragment
 * author : QTwawa
 * date : 2024/7/16 17:59
 */
class RecommendFragment : Fragment() {
    private val mBinding: FgHomeRecommendBinding by lazy {
        FgHomeRecommendBinding.inflate(layoutInflater)
    }
    private val mRecommendViewModel: RecommendViewModel by lazy {
        ViewModelProvider(this)[RecommendViewModel::class.java]
    }
    private lateinit var mAdapter: RecommendAdapter
    private var url: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onScroll()
    }

    private fun initView() {
        mAdapter = RecommendAdapter(this)
        mBinding.rvHomeRecommend.apply {
            adapter = mAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        mRecommendViewModel.recommendData.observe(viewLifecycleOwner) {
            val list = it.filter { element ->
                element.type != "horizontalScrollCard"
            }
            mAdapter.submitList(list)
        }
        mRecommendViewModel.url.observe(viewLifecycleOwner) {
            url = it.replace("http", "https")
        }
    }

    private fun onScroll() {
        mBinding.rvHomeRecommend.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1)){
                    mRecommendViewModel.getNextRecommend(url)
                }
            }
        })
    }


}
