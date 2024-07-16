package com.example.openeye

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.black,null)
        splash()
    }

    private fun splash() {
        Thread{
            Thread.sleep(2000)
            ARouter.getInstance().build("/home/HomeActivity").navigation()
            finish()
        }.start()
    }
}