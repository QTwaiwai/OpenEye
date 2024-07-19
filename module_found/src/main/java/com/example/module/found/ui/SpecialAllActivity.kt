package com.example.module.found.ui

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.base.BaseActivity
import com.example.module.found.adapter.SpecialAllAdapter
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.databinding.ActivitySpecialAllBinding
import com.example.module.found.viewmodel.SpecialViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SpecialAllActivity : BaseActivity<ActivitySpecialAllBinding>() {
    private lateinit var vmSpecial: SpecialViewModel

    override fun afterCreate() {
        mBinding.rvSpecialAll.layoutManager = LinearLayoutManager(this)

        vmSpecial = ViewModelProvider(this)[SpecialViewModel::class.java]
        lifecycleScope.launch {

            vmSpecial.getSpecialAllData()

            vmSpecial.specialAllStateFlow.collectLatest {
                if (it != null) {
                    Log.d("mSpecialList", "afterCreate: $it")
                    mBinding.rvSpecialAll.adapter = SpecialAllAdapter(it)
                }
            }
        }
    }

    override fun getViewBinding(): ActivitySpecialAllBinding =
        ActivitySpecialAllBinding.inflate(layoutInflater)
}