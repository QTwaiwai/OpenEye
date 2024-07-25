package com.example.module.community.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.Item
import com.example.module.community.bean.TabListBean
import com.example.module.community.net.CommunityNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:49
 */
class TabViewModel : ViewModel() {

    private var _mutableTabStateFlow = MutableStateFlow<TabListBean?>(null)
    val tabStateFlow: StateFlow<TabListBean?>
        get() = _mutableTabStateFlow.asStateFlow()

    fun getTabData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = CommunityNet.tabService.getCommunityTab()
                Log.d("ZeqResponse", "getTabData: $response")
                _mutableTabStateFlow.emit(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private var _mutableChildTabStateFlow = MutableStateFlow<List<Item>?>(null)
    val childTabStateFlow: StateFlow<List<Item>?>
        get() = _mutableChildTabStateFlow.asStateFlow()

    private val _url = MutableLiveData<String?>(null)
    val url: LiveData<String?>
        get() = _url

    fun getChildTabData(id: String) {
        viewModelScope.launch{
            try {
                val response: ChildTabBean = if (id == "0") {
                    CommunityNet.childTabService.getChildTab(id, "true", "", "")

                } else {
                    CommunityNet.childTabService.getChildTab(id, "", "", "")
                }
                _url.value = response.nextPageUrl
                Log.d("zeq", "getChildTabData: ${response.itemList}")
                _mutableChildTabStateFlow.value = response.itemList
                //_mutableChildTabStateFlow.emit(response.itemList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMoreChildTabData(id: String, nextPageUrl: String) {
        viewModelScope.launch {
            try {
                val parts = nextPageUrl.split("?")

                val queryParameters = parts[1].split("&")
                val start = queryParameters.firstOrNull { it.startsWith("start=") }
                    ?.substringAfter("start=").toString()
                val num = queryParameters.firstOrNull { it.startsWith("num=") }
                    ?.substringAfter("num=").toString()
                val response = CommunityNet.childTabService.getChildTab(id, "", start, num)
                _url.value = response.nextPageUrl

                _mutableChildTabStateFlow.update {
                    it?.plus(response.itemList)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}