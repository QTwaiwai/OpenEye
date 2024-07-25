package com.example.module.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.module.home.R
import com.example.module.home.bean.DrItem
import com.example.module.home.bean.DvItem


/**
 * description : DailyRvAdapter
 * author : QTwawa
 * date : 2024/7/17 16:08
 */
class DailyRvAdapter(private val context: Fragment) :
    ListAdapter<DrItem, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<DrItem>() {
        override fun areItemsTheSame(oldItem: DrItem, newItem: DrItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DrItem, newItem: DrItem): Boolean {
            return oldItem.data == newItem.data
        }
    }) {
    lateinit var bannerList: List<DvItem>
    lateinit var mViewPager2: ViewPager2
    private var mInitBanner: ((DailyRvAdapter) -> Unit)? = null


    fun onInitBanner(ir: (DailyRvAdapter) -> Unit) {
        mInitBanner = ir
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            else -> 1
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0->{
                val view=LayoutInflater.from(parent.context).inflate(R.layout.banner,parent,false)
                mViewPager2=view.findViewById(R.id.vp2_home_daily)
                mInitBanner?.invoke(this@DailyRvAdapter)
                return BannerViewHolder(view)
            }
            else->{
                val view=LayoutInflater.from(parent.context).inflate(R.layout.item_daily_video,parent,false)
                return DailyRvViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BannerViewHolder->{
            }
            is DailyRvViewHolder->{
                holder.bind(getItem(position))
            }
        }

    }
    fun submitBannerList(bannerNewsList: List<DvItem>) {
        this.bannerList = bannerNewsList
    }
    inner class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    inner class DailyRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivDailyCover: ImageView = itemView.findViewById(R.id.item_iv_home_daily_video)
        private val ivDailyIcon: ImageView = itemView.findViewById(R.id.item_iv_home_daily_icon)
        private val tvDailyTitle: TextView = itemView.findViewById(R.id.item_tv_home_daily_title)
        private val tvDailyAuthor: TextView = itemView.findViewById(R.id.item_tv_home_daily_author)
        private val tvDailyTime: TextView = itemView.findViewById(R.id.item_tv_home_daily_time)
        private val tvDailyTag: TextView = itemView.findViewById(R.id.item_tv_home_daily_tag)

        @SuppressLint("SetTextI18n")
        fun bind(data: DrItem) {
            Glide.with(itemView.context)
                .load(data.data.cover.detail)
                .into(ivDailyCover)
            Glide.with(itemView.context)
                .load(data.data.author.icon).circleCrop()
                .into(ivDailyIcon)
            tvDailyTitle.text = data.data.title
            tvDailyAuthor.text = data.data.author.name
            tvDailyTag.text = "#" + data.data.category
            val long = data.data.duration.toLong()
            val minuter = (long / 60).toInt()
            val second = (long % 60).toInt()
            var time: String = if (minuter < 10) "0$minuter" else minuter.toString()
            time += ":"
            time += if (second < 10) "0$second" else second.toString()
            tvDailyTime.text = time
            //设计点击事件
            ivDailyCover.setOnClickListener {
                ARouter.getInstance().build("/video/VideoActivity")
                    .withString("title",data.data.title)
                    .withString("author",data.data.author.name)
                    .withString("description",data.data.description)
                    .withInt("likes",data.data.consumption.collectionCount)
                    .withString("tag",data.data.category)
                    .withInt("share",data.data.consumption.shareCount)
                    .withInt("star",data.data.consumption.realCollectionCount)
                    .withString("url",data.data.playUrl)
                    .withInt("id",data.data.id)
                    .navigation(context.activity?.application?.applicationContext)
            }
        }
    }
}

//tvTitle.text=intent.getStringExtra("title")
//tvDescription.text=intent.getStringExtra("description")
//tvLikes.text=intent.getStringExtra("likes")
//tvTag.text="#"+intent.getStringExtra("tag")
//tvStar.text=intent.getStringExtra("star")
//tvShare.text=intent.getStringExtra("share")
//toolbar.title=intent.getStringExtra("title")
//url=intent.getStringExtra("url")
//mOthersViewModel.getOthersData(intent.getIntExtra("id",0))