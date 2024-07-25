package com.example.module.found.ui.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.lib.base.BaseActivity
import com.example.module.found.MyImage
import com.example.module.found.adapter.ClassifyDetailAdapter
import com.example.module.found.bean.ClassifyDetail
import com.example.module.found.databinding.ActivityClassifyDetailBinding
import com.example.module.found.viewmodel.ClassifyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ClassifyDetailActivity : BaseActivity<ActivityClassifyDetailBinding>() {
    private lateinit var classifyDetailList: ClassifyDetail
    private lateinit var mViewModel: ClassifyViewModel

    companion object {
        fun startDetail(
            context: Context,
            id: String,
            desc: String,
            tvClassify: TextView,
            position: Int
        ) {

            val intent = Intent(context, ClassifyDetailActivity::class.java).apply {
                putExtra("id", id)
                putExtra("desc", desc)
                putExtra("tvClassify", tvClassify.text)
                putExtra("position", position)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                context as Activity,
                tvClassify,
                tvClassify.transitionName
            )
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun afterCreate() {
        //设置toolbar的标题，和对应栏目的描述
        setSupportActionBar(mBinding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.collapsingToolbarDetail.title = intent.getStringExtra("tvClassify")
        mBinding.tvDetailDesc.text = intent.getStringExtra("desc")

        Glide.with(this)
            .load(MyImage.imageArray[intent.getIntExtra("position", 0)])
            .into(mBinding.imgDetail)

        // 初始化 ViewModel
        mViewModel = ViewModelProvider(this)[ClassifyViewModel::class.java]
        val id: String = intent.getStringExtra("id").toString()
        lifecycleScope.launch {
            // 获取数据
            mViewModel.getClassifyDetailData(id)
            // 观察数据变化
            mViewModel.detailStateFlow.collectLatest {
                it?.let {
                    classifyDetailList = it
                    Log.d("classifyDetail", "onViewCreated: $it")
                    initRv()
                }
            }
        }

        //返回顶部
        mBinding.btnFloat.setOnClickListener {
            mBinding.rvClassifyDetail.smoothScrollToPosition(0)
        }
    }

    private fun initRv() {
        mBinding.rvClassifyDetail.apply {
            layoutManager = LinearLayoutManager(this@ClassifyDetailActivity)
            adapter = ClassifyDetailAdapter(classifyDetailList)
        }
    }

    //按下 Home 按钮的默认图标，返回上一个界面
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //获取布局文件
    override fun getViewBinding(): ActivityClassifyDetailBinding =
        ActivityClassifyDetailBinding.inflate(layoutInflater)
}