package com.example.module_video.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_video.adapter.OthersAdapter
import com.example.module_video.databinding.ActivityVideoBinding
import com.example.module_video.viewmodel.OthersViewModel
import xyz.doikki.videocontroller.StandardVideoController
import xyz.doikki.videocontroller.component.CompleteView
import xyz.doikki.videocontroller.component.ErrorView
import xyz.doikki.videocontroller.component.GestureView
import xyz.doikki.videocontroller.component.VodControlView
import xyz.doikki.videoplayer.player.VideoView


@Route(path="/video/VideoActivity")
class VideoActivity : AppCompatActivity() {
    private val mBinding: ActivityVideoBinding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }
    private val mOthersViewModel: OthersViewModel by lazy {
        OthersViewModel()
    }
    private val mAdapter: OthersAdapter by lazy {
        OthersAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        ARouter.getInstance().inject(this)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        mBinding.apply {
            tvTitle.text=intent.getStringExtra("title")
            tvAuthor.text=intent.getStringExtra("author")
            tvDescription.text=intent.getStringExtra("description")
            tvLikes.text=intent.getIntExtra("likes",0).toString()
            tvTag.text="#"+intent.getStringExtra("tag")
            tvStar.text=intent.getIntExtra("star",0).toString()
            tvShare.text=intent.getIntExtra("share",0).toString()
            toolbar.title=intent.getStringExtra("title")
        }
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        val url=intent.getStringExtra("url")?:""
        mOthersViewModel.getOthersData(intent.getIntExtra("id",0))
        mOthersViewModel.othersData.observe(this@VideoActivity){
            val list = it.filter {element->
                element.type == "videoSmallCard"
            }
            mAdapter.submitList(list)
        }
        mBinding.rvOthers.apply {
            adapter = mAdapter
            layoutManager= LinearLayoutManager(this@VideoActivity)
        }
        playVideo(url)
    }

    private fun playVideo(url: String) {
        val controller = StandardVideoController(this)
        controller.addDefaultControlComponent("title", false)
        mBinding.video.setVideoController(controller) //设置控制器
        mBinding.video.setUrl(url) //设置视频地址
        mBinding.video.start() //开始播放，不调用则不自动播放
    }
    override fun onPause() {
        super.onPause()
        mBinding.video.pause()
    }

    override fun onResume() {
        super.onResume()
        mBinding.video.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.video.release()
    }
    override fun onBackPressed() {
        if (!mBinding.video.onBackPressed()) {
            super.onBackPressed()
        }
    }
}
