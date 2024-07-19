package com.example.module_video.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_video.R
import com.example.module_video.adapter.OthersAdapter
import com.example.module_video.databinding.ActivityVideoBinding
import com.example.module_video.viewmodel.OthersViewModel

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
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.white,null)
        initView()
    }

    private fun initView() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
//        mBinding.video.player=ExoPlayer.Builder(this).build()
//        playVideo()
        mOthersViewModel.getOthersData("https://baobab.kaiyanapp.com/api/v4/video/related?id=186175")
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
    }

//    private fun playVideo() {
//        mBinding.video.player?.run {
//            repeatMode=Player.REPEAT_MODE_ONE
//            playWhenReady=true
//            val mediaItem= MediaItem.fromUri("https://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/d336cfef0ca7e778dfca5d9ed56ec6ce.mp4")
//            setMediaItem(mediaItem)
//            prepare()
//        }
//    }
//    override fun onPause() {
//        super.onPause()
//        if (mBinding.video.player != null) {
//            mBinding.video.player?.playWhenReady = false
//        }
//    }
//    override fun onResume() {
//        super.onResume()
//        mBinding.video.player.let {
//            it?.playWhenReady = true
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if (mBinding.video.player != null) {
//            mBinding.video.player?.playWhenReady = false
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mBinding.video.player?.release()
//    }
}
