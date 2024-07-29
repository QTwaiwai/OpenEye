package com.example.module.community

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.base.BaseActivity
import com.example.lib.base.NetStatus
import com.example.module.community.adapter.ChildTabAdapter
import com.example.module.community.databinding.ActivityChildTabBinding
import com.example.module.community.net.loadstate.CommunityLoadStateAdapter
import com.example.module.community.viewmodel.TabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.IOException

class ChildTabActivity : BaseActivity<ActivityChildTabBinding>() {
    private lateinit var vmTab: TabViewModel
    private val childAdapter: ChildTabAdapter by lazy { ChildTabAdapter() }
    private val mLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this@ChildTabActivity) }
    private val DataId by lazy {
        intent.getStringExtra("id").toString()
    }

    companion object {
        fun startChild(context: Context, id: String, name: String) {
            val intent = Intent(context, ChildTabActivity::class.java).apply {
                putExtra("id", id)
                putExtra("title", name)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化ViewModel
        vmTab = ViewModelProvider(this)[TabViewModel::class.java]
        //设置标题
        mBinding.tvChildTitle.text = intent.getStringExtra("title").toString()
        mBinding.tvChildTitle.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC)

        //初始化RecyclerView
        mBinding.rvChildTab.apply {
            layoutManager = mLayoutManager
            //adapter = childAdapter
            adapter = childAdapter.withLoadStateFooter(
                footer = CommunityLoadStateAdapter(retry = {
                    childAdapter.retry()
                })
            )
        }

        getChildData(DataId)
        //返回顶部
        mBinding.btnCommunityFloat.setOnClickListener {
            mBinding.rvChildTab.smoothScrollToPosition(0)
        }
        aboutLoad()
    }

    private fun getChildData(id: String) {
        //请求ChildTab的数据
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vmTab.getChildTabData(id).collectLatest {
                    childAdapter.submitData(it)
                }
            }
        }

        childAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Error) {

                Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show()
                when ((it.refresh as LoadState.Error).error) {
                    is IOException -> {
                        Toast.makeText(this, "网络错误，请检查网络", Toast.LENGTH_SHORT).show()
                        mBinding.progressBar.visibility = View.GONE
                    }

                    else -> {
                        Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    private fun aboutLoad() {

        mBinding.swipeRefresh.apply {
            setColorSchemeResources(R.color.lemon_yellow)
            setOnRefreshListener {
                getChildData(DataId)
                isRefreshing = false
            }
        }
    }

    override fun getViewBinding(): ActivityChildTabBinding =
        ActivityChildTabBinding.inflate(layoutInflater)
}