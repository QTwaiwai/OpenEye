package com.example.module.community

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.base.BaseActivity
import com.example.module.community.adapter.ChildTabAdapter
import com.example.module.community.databinding.ActivityChildTabBinding
import com.example.module.community.viewmodel.ChildTabViewModel
import com.example.module.community.viewmodel.TabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChildTabActivity : BaseActivity<ActivityChildTabBinding>() {
    private lateinit var vmTab: TabViewModel
    private var url: String? = null
    private val childAdapter: ChildTabAdapter by lazy { ChildTabAdapter() }
    private val mLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this@ChildTabActivity) }

    companion object {
        fun startChild(context: Context, id: String, name: String) {
            val intent = Intent(context, ChildTabActivity::class.java).apply {
                putExtra("id", id)
                putExtra("title", name)
            }
            context.startActivity(intent)
        }
    }

    override fun afterCreate() {
        //初始化ViewModel
        vmTab = ViewModelProvider(this)[TabViewModel::class.java]
        //设置标题
        mBinding.tvChildTitle.text = intent.getStringExtra("title").toString()
        mBinding.tvChildTitle.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC)

        val id = intent.getStringExtra("id").toString()

        //初始化RecyclerView
        mBinding.rvChildTab.apply {
            layoutManager = mLayoutManager
            adapter = childAdapter
        }

        getChildData(id)
        initListener(id)

    }

    private fun getChildData(id: String) {
        vmTab.getChildTabData(id)
        //请求ChildTab的数据
        lifecycleScope.launch {

            vmTab.childTabStateFlow.collect {
                if (it != null) {
                    Log.d("zzzzzzzzzeeee", "getChildData: $it")
                    childAdapter.submitList(it)

                }
            }
        }

        vmTab.url.observe(this@ChildTabActivity) { nextPageUrl ->
            Log.d("nextPageUrl", "getChildData: $nextPageUrl")

            url = nextPageUrl?.replace("http", "https")
        }

    }

    private fun initListener(id: String) {

        mBinding.rvChildTab.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) { // 向下滚动
                    val visibleItemCount = mLayoutManager.childCount
                    val totalItemCount = mLayoutManager.itemCount
                    val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (url != null && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        vmTab.getMoreChildTabData(id, url!!)
                    } else {
                        mBinding.tvEnd.visibility = View.VISIBLE

                    }
                }
            }
        })
    }

    override fun getViewBinding(): ActivityChildTabBinding =
        ActivityChildTabBinding.inflate(layoutInflater)
}