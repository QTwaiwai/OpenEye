package com.example.module.community

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.base.BaseActivity
import com.example.module.community.adapter.ChildTabAdapter
import com.example.module.community.adapter.TabAdapter
import com.example.module.community.databinding.ActivityChildTabBinding
import com.example.module.community.viewmodel.TabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChildTabActivity : BaseActivity<ActivityChildTabBinding>() {
    private lateinit var vmTab: TabViewModel

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

        mBinding.tvChildTitle.text = intent.getStringExtra("title").toString()
        mBinding.tvChildTitle.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC)

        mBinding.rvChildTab.layoutManager = LinearLayoutManager(this)

        //请求ChildTab的数据
        val id = intent.getStringExtra("id").toString()
        Log.d("getId...", "afterCreate: $id")
        lifecycleScope.launch {
            vmTab.getChildTabData(id)

            vmTab.childTabStateFlow.collectLatest {
                if (it != null) {
                    mBinding.rvChildTab.adapter = ChildTabAdapter(it)
                }
            }
        }
    }

    override fun getViewBinding(): ActivityChildTabBinding =
        ActivityChildTabBinding.inflate(layoutInflater)
}