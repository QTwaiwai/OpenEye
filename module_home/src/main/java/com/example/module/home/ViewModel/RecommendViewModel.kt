package com.example.module.home.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lib_net.RetrofitClient
import com.example.module.home.bean.Item
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
class RecommendViewModel():ViewModel() {
    private val _recommendData = MutableLiveData<List<Item>>()
    val recommendData: LiveData<List<Item>>
        get() = _recommendData
    private val _url= MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url
    private val _nextRecommendData = MutableLiveData<List<Item>>()
    val nextRecommendData: LiveData<List<Item>>
        get() = _nextRecommendData
    private  val service = RetrofitClient.getService(RecommendService::class.java)

    init {
        getRecommend()
    }

    private fun getRecommend() {
       service
           .getRecommend()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(object : Observer<RecommendData> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: RecommendData) {
                    Log.e("NET", "onNext: ${t.itemList}")
                    _url.value= t.nextPageUrl
                    _recommendData.postValue(t.itemList)
                }

                override fun onError(e: Throwable) {
                    Log.e("NET", "onError: ${e.message}")
                }

                override fun onComplete() {
                }
            })
    }

    fun getNextRecommend(url: String){
        service
            .getNextRecommend(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<RecommendData> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.e("NET2", "onError: ${e.message}")
                }

                override fun onComplete() {
                }

                override fun onNext(t: RecommendData) {
                    _url.value=t.nextPageUrl
                    _recommendData.value= _recommendData.value?.plus(t.itemList)
                }
            })
    }

}
