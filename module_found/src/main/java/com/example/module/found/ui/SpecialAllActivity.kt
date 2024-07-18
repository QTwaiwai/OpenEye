package com.example.module.found.ui

import android.view.MenuItem
import com.example.lib.base.BaseActivity
import com.example.module.found.databinding.ActivitySpecialAllBinding

class SpecialAllActivity : BaseActivity<ActivitySpecialAllBinding>() {
    override fun afterCreate() {

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewBinding(): ActivitySpecialAllBinding =
        ActivitySpecialAllBinding.inflate(layoutInflater)
}