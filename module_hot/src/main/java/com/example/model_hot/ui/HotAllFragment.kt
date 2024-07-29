package com.example.model_hot.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.model_hot.R
import com.example.model_hot.adapter.HotRankAdapter
import com.example.model_hot.databinding.FragmentHotAllBinding
import com.example.model_hot.databinding.FragmentHotMonthBinding
import com.example.model_hot.viewmodel.RankViewModel

class HotAllFragment(private val strategy: String) : Fragment() {
    private val mRankViewModel: RankViewModel by lazy {
        ViewModelProvider(this)[RankViewModel::class.java]
    }
    private val mBinding: FragmentHotAllBinding by lazy {
        FragmentHotAllBinding.inflate(layoutInflater)
    }
    private val mAdapter: HotRankAdapter by lazy { HotRankAdapter() }
    private val mLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireActivity())
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
        mRankViewModel.getHot(strategy)

        mBinding.rvHistorical.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
        mRankViewModel.historicalData
            .observe(viewLifecycleOwner) {
                mAdapter.submitList(it)
            }
    }

}