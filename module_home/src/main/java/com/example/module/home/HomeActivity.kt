package com.example.module.home

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.module.home.databinding.ActivityHomeBinding
import com.example.module.home.ui.HomeFragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author: QT
 * @date: 2024/7/16
 * description:主框架
 */

@Route(path = "/home/HomeActivity")
class HomeActivity:AppCompatActivity() {
    private val mBinding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.black,null)
        ARouter.getInstance().inject(this)
        initView()
    }

    private fun initView() {
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fg_home_view, homeFragment)
            .commit()
        mBinding.btnHomeNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_home->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fg_home_view, homeFragment)
                        .commit()
                }
            }
            true
            }
        }
    }
