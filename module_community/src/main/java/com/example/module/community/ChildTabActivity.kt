package com.example.module.community

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.base.BaseActivity
import com.example.module.community.adapter.ChildTabAdapter
import com.example.module.community.databinding.ActivityChildTabBinding
import com.example.module.community.viewmodel.TabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChildTabActivity : BaseActivity<ActivityChildTabBinding>() {
    private lateinit var vmTab: TabViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        //返回顶部
        mBinding.btnCommunityFloat.setOnClickListener {
            mBinding.rvChildTab.smoothScrollToPosition(0)
        }
    }

    private fun getChildData(id: String) {
        //请求ChildTab的数据
        lifecycleScope.launch {

            vmTab.getChildTabData(id).collectLatest {
                childAdapter.submitData(it)
                mBinding.tvEnd.visibility = View.VISIBLE
            }
        }
    }

    override fun getViewBinding(): ActivityChildTabBinding =
        ActivityChildTabBinding.inflate(layoutInflater)
}