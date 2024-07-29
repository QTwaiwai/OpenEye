package com.example.module.found.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.base.BaseActivity
import com.example.module.found.adapter.SpecialAllAdapter
import com.example.module.found.databinding.ActivitySpecialAllBinding
import com.example.module.found.viewmodel.SpecialAllViewModel
import kotlinx.coroutines.launch

class SpecialAllActivity : BaseActivity<ActivitySpecialAllBinding>() {
    private lateinit var vmAllSpecial: SpecialAllViewModel
    private var url: String? = null

    private val mAdapter by lazy {
        SpecialAllAdapter()
    }
    private val mLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this@SpecialAllActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // setContentView(mBinding.root)

        mBinding.rvSpecialAll.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        vmAllSpecial = ViewModelProvider(this)[SpecialAllViewModel::class.java]

        initData()
        initListener()
    }

    private fun initData() {
        vmAllSpecial.getSpecialAllData()

        lifecycleScope.launch {

            vmAllSpecial.allStateFlow.collect {
                if (it != null) {
                    Log.d("zeqSpecialList", "afterCreate: $it")
                    mAdapter.submitList(it)
                }
            }
        }

        vmAllSpecial.url.observe(this) { nextPageUrl ->
            Log.d("nextPageUrl", "getChildData: $nextPageUrl")

            url = nextPageUrl?.replace("http", "https")
        }
    }

    private fun initListener() {
        mBinding.rvSpecialAll.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) { // 向下滚动
                    val visibleItemCount = mLayoutManager.childCount
                    val totalItemCount = mLayoutManager.itemCount
                    val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (url != null && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        Log.d("zeqUrl", "到下滑的地方了$url")
                        vmAllSpecial.getMoreSpecial(url!!)
                    }
                }
            }
        })
    }

    override fun getViewBinding(): ActivitySpecialAllBinding =
        ActivitySpecialAllBinding.inflate(layoutInflater)
}