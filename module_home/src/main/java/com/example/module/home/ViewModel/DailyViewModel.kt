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
import com.example.module.home.pagingsource.DailySource
import com.example.module.home.bean.DailyVp2Data
import com.example.module.home.bean.DvItem
import com.example.module.home.service.DailyVp2Service
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * description : DailyViewModel
 * author : QTwawa
 * date : 2024/7/17 19:03
 */
class DailyViewModel : ViewModel() {

    private val _url = MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url
    private val _dailyVp2Data = MutableLiveData<List<DvItem>>()
    val dailyVpData: LiveData<List<DvItem>>
        get() = _dailyVp2Data
    private val _isConnect = MutableLiveData<Boolean>()
    val isConnect: LiveData<Boolean>
        get() = _isConnect
    private val serviceVp2 = RetrofitClient.getService(DailyVp2Service::class.java)

    init {
        getDailyVpData()
//        getDailyRvData()
    }

    fun getDailyVpData() {
        serviceVp2
            .getDailyVp2Data()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DailyVp2Data> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    _isConnect.postValue(false)
                }

                override fun onComplete() {
                }

                override fun onNext(t: DailyVp2Data) {
                    Log.d("NET", t.toString())
                    _isConnect.postValue(true)
                    _dailyVp2Data.postValue(t.itemList)
                }
            })
    }

//    fun getDailyRvData() {
//        serviceRv
//            .getDailyRvData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<DailyRvData> {
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onNext(t: DailyRvData) {
//                    _isConnect.value=true
//                    _url.value = t.nextPageUrl
//                    _dailyRvData.postValue(t.itemList)
//                }
//
//                override fun onError(e: Throwable) {
//                    _isConnect.value=false
//                }
//
//                override fun onComplete() {
//                }
//            })
//    }
        val dailyData = Pager(PagingConfig(pageSize = 10)){
            DailySource()
        }.flow.cachedIn(viewModelScope)

//    fun getNextDailyRvData(url: String) {
//        serviceRv
//            .getNextDailyRvData(url)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<DailyRvData> {
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onError(e: Throwable) {
//                    _isConnect.value=false
//                }
//
//                override fun onComplete() {
//                }
//
//                override fun onNext(t: DailyRvData) {
//                    _isConnect.value=true
//                    _url.value = t.nextPageUrl
//                    _dailyRvData.value = _dailyRvData.value?.plus(t.itemList)
//                }
//            })
//    }
}