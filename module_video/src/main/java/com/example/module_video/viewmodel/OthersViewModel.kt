package com.example.module_video.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lib_net.RetrofitClient
import com.example.module_video.bean.Item
import com.example.module_video.bean.OthersData
import com.example.module_video.service.OthersService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * author : QTwawa
 * date : 2024/7/18 23:02
 */
class OthersViewModel:ViewModel() {
    private val _othersData = MutableLiveData<List<Item>>()
    val othersData: MutableLiveData<List<Item>>
        get() = _othersData
    private val othersService= RetrofitClient.getService(OthersService::class.java)

    fun getOthersData(id:Int){
        othersService.getOthersData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<OthersData> {
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: OthersData) {
                    _othersData.value=t.itemList
                }
                override fun onError(e: Throwable) {
                    Log.e("NET", "onError: ${e.message}")
                }
                override fun onComplete() {
                }
            })
    }
}