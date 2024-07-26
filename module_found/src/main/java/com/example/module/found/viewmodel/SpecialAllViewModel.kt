package com.example.module.found.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.net.FoundNet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/23 11:39
 */
class SpecialAllViewModel : ViewModel() {
    private var _mutableAllStateFlow = MutableStateFlow<List<SpecialDetailBean>?>(null)
    val allStateFlow: StateFlow<List<SpecialDetailBean>?>
        get() = _mutableAllStateFlow.asStateFlow()

    private val _url = MutableLiveData<String?>(null)
    val url: LiveData<String?>
        get() = _url

    fun getSpecialAllData() {
        viewModelScope.launch {
            try {
                val response = FoundNet.specialService.getSpecial()
                val responseId = response.itemList.map {
                    it.data.id.toString()
                }

                _url.value = response.nextPageUrl

                Log.d("zeq", "getSpecialAllData: $url")
                val responseList = mutableListOf<SpecialDetailBean>()
                for (id in responseId) {
                    val responseItem = FoundNet.specialService.getSpecialDetail(id)

                    responseList.add(responseItem)
                    Log.d("zeq", "getSpecialAllData: $responseItem")
                }

                _mutableAllStateFlow.emit(responseList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMoreSpecial(url: String) {
        viewModelScope.launch {
            try {
                val parts = url.split("?")

                val queryParameters = parts[1].split("&")
                val start = queryParameters.firstOrNull { it.startsWith("start=") }
                    ?.substringAfter("start=").toString()
                val num = queryParameters.firstOrNull { it.startsWith("num=") }
                    ?.substringAfter("num=").toString()

                val response = FoundNet.specialService.getMoreSpecial(start, num)
                val responseId = response.itemList.map {
                    it.data.id.toString()
                }
                Log.d("zeq", "getMoreSpecial: $responseId")
                _url.value = response.nextPageUrl
                Log.d("zeqq", "getMoreSpecial: $url")

                val responseList = mutableListOf<SpecialDetailBean>()
                for (id in responseId) {
                    val responseItem = FoundNet.specialService.getSpecialDetail(id)

                    responseList.add(responseItem)
                }
                _mutableAllStateFlow.update {
                    it?.plus(responseList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}