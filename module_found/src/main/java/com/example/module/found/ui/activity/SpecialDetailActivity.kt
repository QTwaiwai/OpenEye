package com.example.module.found.ui.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.lib.base.BaseActivity
import com.example.module.found.adapter.SpecialDetailAdapter
import com.example.module.found.databinding.ActivitySpecialDetailBinding
import com.example.module.found.viewmodel.SpecialViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SpecialDetailActivity : BaseActivity<ActivitySpecialDetailBinding>() {
    private lateinit var vmSpecial: SpecialViewModel

    companion object {
        fun actionStart(context: Context, id: String, imgUrl: String, imgSpecial: ImageView) {

            val intent = Intent(context, SpecialDetailActivity::class.java).apply {
                putExtra("id", id)
                putExtra("imgUrl", imgUrl)
               //putExtra("imgId", imgSpecial.id)
            }

            val option = ActivityOptions.makeSceneTransitionAnimation(
                context as Activity,
                imgSpecial,
                imgSpecial.transitionName
            )

            context.startActivity(intent,option.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(mBinding.root)

        mBinding.tvEnd.setTypeface(null, Typeface.BOLD_ITALIC)
        val id: String = intent.getStringExtra("id").toString()
        val imgVideoUrl: String = intent.getStringExtra("imgUrl").toString()

        mBinding.rvSpecialDetail.layoutManager = LinearLayoutManager(this)

        vmSpecial = ViewModelProvider(this)[SpecialViewModel::class.java]
        lifecycleScope.launch {

            vmSpecial.getSpecialData(id)

            vmSpecial.specialStateFlow.collectLatest {
                if (it != null) {
                    Log.d("mSpecialList", "afterCreate: $it")
                    mBinding.rvSpecialDetail.adapter = SpecialDetailAdapter(it)

                    mBinding.tvSpecialTitle.text = it.brief
                    mBinding.tvSpecialDesc.text = it.text
                }
            }
        }

        Glide.with(this)
            .load(imgVideoUrl)
            .into(mBinding.imgSpecialHead)
    }

    override fun getViewBinding(): ActivitySpecialDetailBinding =
        ActivitySpecialDetailBinding.inflate(layoutInflater)
}