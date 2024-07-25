package com.example.module.home.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.lib_net.RetrofitClient
import com.example.module.home.RecommendSource
import com.example.module.home.bean.Rec
import com.example.module.home.bean.RecItem
import com.example.module.home.bean.RecommendData
import com.example.module.home.service.RecommendService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * description : RecommendViewModel
 * author : QTwawa
 * date : 2024/7/16 17:59
 */
class RecommendViewModel : ViewModel() {
    private val _recommendData = MutableLiveData<List<RecItem>>()
    val recommendData: LiveData<List<RecItem>>
        get() = _recommendData
    private val _url = MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url
    private val _nextRecommendData = MutableLiveData<List<RecItem>>()
    val nextRecommendData: LiveData<List<RecItem>>
        get() = _nextRecommendData
    private val service = RetrofitClient.getService(RecommendService::class.java)

    val listData = Pager(PagingConfig(pageSize = 5)){
        RecommendSource()
    }.flow.cachedIn(viewModelScope)

}
