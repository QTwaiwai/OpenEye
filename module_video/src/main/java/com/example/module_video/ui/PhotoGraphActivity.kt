package com.example.module_video.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.module_video.R
import com.example.module_video.adapter.PhotoAdapter
import com.example.module_video.databinding.ActivityPhotoGraphBinding
import java.io.Serializable

/**
 * author : QTwawa
 * date : 2024/7/21 14:20
 */
@Route(path = "/video/PhotoGraphActivity")
class PhotoGraphActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context, source: Source, bundle: Bundle? = null) {
            context.startActivity(
                Intent(
                    context,
                    PhotoGraphActivity::class.java
                ).putExtra("source", source), bundle
            )
        }

        data class Source(var picUrls: List<String>?) : Serializable
    }

    private val mBinding: ActivityPhotoGraphBinding by lazy {
        ActivityPhotoGraphBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        ARouter.getInstance().inject(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.black, null)
        initView()
    }

    private fun initView() {
        mBinding.vp2Photo.apply {
            val source = intent.getSerializableExtra("source")!! as Source
            adapter = PhotoAdapter(source.picUrls!!)

        }
    }
}
