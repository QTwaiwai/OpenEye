package com.example.module.home.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.module.home.pagingsource.RecommendSource

/**
 * description : RecommendViewModel
 * author : QTwawa
 * date : 2024/7/16 17:59
 */
class RecommendViewModel : ViewModel() {
//    private val _recommendData = MutableLiveData<List<RecItem>>()
//    val recommendData: LiveData<List<RecItem>>
//        get() = _recommendData
//    private val _url = MutableLiveData<String>()
//    val url: LiveData<String>
//        get() = _url
//    private val _nextRecommendData = MutableLiveData<List<RecItem>>()
//    val nextRecommendData: LiveData<List<RecItem>>
//        get() = _nextRecommendData
//    private val service = RetrofitClient.getService(RecommendService::class.java)

    val listData = Pager(PagingConfig(pageSize = 10)){
        RecommendSource()
    }.flow.cachedIn(viewModelScope)

}
