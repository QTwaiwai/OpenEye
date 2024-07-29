package com.example.model_hot.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model_hot.bean.Item
import com.example.model_hot.bean.RankBean
import com.example.model_hot.net.HotNetRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/25 12:07
 */
class RankViewModel : ViewModel() {
    private var _mutableMonthlyData = MutableLiveData<List<Item>>()
    val monthlyData: MutableLiveData<List<Item>>
        get() = _mutableMonthlyData

    private var _mutableHistoricalData = MutableLiveData<List<Item>>()
    val historicalData: MutableLiveData<List<Item>>
        get() = _mutableHistoricalData

    fun getHot(strategy: String) {
        HotNetRepo.rankService
            .getRankList(strategy)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<RankBean> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

                override fun onNext(t: RankBean) {
                    when (strategy) {
                        "monthly" ->
                            _mutableMonthlyData.value = t.itemList

                        "historical" ->
                            _mutableHistoricalData.value = t.itemList

                    }
                }
            })
    }

}