package com.example.module_video.ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.module_video.R

/**
 * author : QTwawa
 * date : 2024/7/21 14:20
 */
@Route(path = "/video/PhotoGraphActivity")
class PhotoGraphActivity: AppCompatActivity() {
    private lateinit var mIvPhotoGraph: ImageView
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_photo_graph)
            ARouter.getInstance().inject(this)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.black, null)
            initView()
        }

    private fun initView() {
        mIvPhotoGraph=findViewById(R.id.iv_photo)
        Glide.with(this).load(intent.getStringExtra("picture")).into(mIvPhotoGraph)
    }


}