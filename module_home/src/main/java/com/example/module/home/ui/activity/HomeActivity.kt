package com.example.module.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.module.home.databinding.ActivityHomeBinding
import com.example.module.home.ui.fragment.HomeFragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.model_hot.ui.HotFragment
import com.example.module.community.CommunityFragment
import com.example.module.found.ui.fragment.FoundFragment

/**
 * @author: QT
 * @date: 2024/7/16
 * description:主框架
 */

@Route(path = "/home/HomeActivity")
class HomeActivity : AppCompatActivity() {
    private val mBinding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.black, null)
        ARouter.getInstance().inject(this)
        initView()
    }

    @SuppressLint("CommitTransaction")
    private fun initView() {
        val communityFragment = CommunityFragment()
        val foundFragment = FoundFragment()
        val homeFragment = HomeFragment()
        val hotFragment = HotFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fg_home_view, homeFragment)
            .commit()
        mBinding.btnHomeNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fg_home_view, homeFragment)
                        .commit()
                }

                R.id.item_found -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fg_home_view, foundFragment)
                        .commit()
                }

                R.id.item_community -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fg_home_view, communityFragment)
                        .commit()
                }

                R.id.item_hot -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fg_home_view, hotFragment)
                        .commit()
                }
            }
            true
        }
    }
}
