package com.example.module.home.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lib_net.RetrofitClient
import com.example.module.home.bean.DailyRvData
import com.example.module.home.bean.DrItem
import com.example.module.home.service.DailyRvService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * description : DailyViewModel
 * author : QTwawa
 * date : 2024/7/17 19:03
 */
class DailyViewModel:ViewModel() {
    private val _dailyRvData = MutableLiveData<List<DrItem>>()
    val dailyRvData: LiveData<List<DrItem>>
        get() = _dailyRvData
    private val _url= MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url
    private val _nextDailyRvData = MutableLiveData<List<DrItem>>()
    val nextDailyRvData: LiveData<List<DrItem>>
        get() = _nextDailyRvData
    private  val service = RetrofitClient.getService(DailyRvService::class.java)

    init {
        getDailyRvData()
    }

    private fun getDailyRvData() {
        service
            .getDailyRvData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DailyRvData> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: DailyRvData) {
                    _url.value= t.nextPageUrl
                    _dailyRvData.postValue(t.itemList)
                }

                override fun onError(e: Throwable) {
                    Log.e("NET", "onError: ${e.message}")
                }

                override fun onComplete() {
                }
            })
    }

    fun getNextDailyRvData(url: String){
        service
            .getNextDailyRvData(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DailyRvData> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.e("NET2", "onError: ${e.message}")
                }

                override fun onComplete() {
                }

                override fun onNext(t: DailyRvData) {
                    _url.value=t.nextPageUrl
                    _dailyRvData.value= _dailyRvData.value?.plus(t.itemList)
                }
            })
    }
}