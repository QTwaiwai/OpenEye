package com.example.lib.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.example.lib.base.databinding.ActivityBaseBinding

abstract class BaseActivity<vb : ViewBinding> : AppCompatActivity() {
    protected val mBinding by lazy {
        getViewBinding()
    }

    protected abstract fun afterCreate()
    protected abstract fun getViewBinding(): vb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(mBinding.root)
        afterCreate()
    }
}