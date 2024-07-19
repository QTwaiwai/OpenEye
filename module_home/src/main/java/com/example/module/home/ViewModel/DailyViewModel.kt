package com.example.module.home.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_net.RetrofitClient
import com.example.module.home.bean.DailyRvData
import com.example.module.home.bean.DailyVp2Data
import com.example.module.home.bean.DrItem
import com.example.module.home.bean.DvItem
import com.example.module.home.service.DailyRvService
import com.example.module.home.service.DailyVp2Service
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.await

/**
 * description : DailyViewModel
 * author : QTwawa
 * date : 2024/7/17 19:03
 */
class DailyViewModel : ViewModel() {
    private val _dailyRvData = MutableLiveData<List<DrItem>>()
    val dailyRvData: LiveData<List<DrItem>>
        get() = _dailyRvData
    private val _url = MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url
    private val _dailyVp2Data = MutableLiveData<List<DvItem>>()
    val dailyVpData: MutableLiveData<List<DvItem>>
        get() = _dailyVp2Data
    private val serviceRv = RetrofitClient.getService(DailyRvService::class.java)
    private val serviceVp2 = RetrofitClient.getService(DailyVp2Service::class.java)

    init {
        getDailyVpData()
        getDailyRvData()
    }

    private fun getDailyVpData() {
        serviceVp2
            .getDailyVp2Data()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DailyVp2Data> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

                override fun onNext(t: DailyVp2Data) {
                    _dailyVp2Data.postValue(t.itemList)
                }
            })
    }

    private fun getDailyRvData() {
        serviceRv
            .getDailyRvData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DailyRvData> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: DailyRvData) {
                    _url.value = t.nextPageUrl
                    _dailyRvData.postValue(t.itemList)
                }

                override fun onError(e: Throwable) {
                    Log.e("NET", "onError: ${e.message}")
                }

                override fun onComplete() {
                }
            })
    }

    fun getNextDailyRvData(url: String) {
        serviceRv
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
                    Log.d("NET", "onNext: ${t.itemList}")
                    _url.value = t.nextPageUrl
                    _dailyRvData.value = _dailyRvData.value?.plus(t.itemList)
                }
            })
    }
}