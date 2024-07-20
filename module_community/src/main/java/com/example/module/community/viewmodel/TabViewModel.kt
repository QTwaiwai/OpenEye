package com.example.module.community.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.TabListBean
import com.example.module.community.net.CommunityNet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        viewModelScope.launch {
            try {
                val response = CommunityNet.tabService.getCommunityTab()
                _mutableTabStateFlow.emit(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private var _mutableChildTabStateFlow = MutableStateFlow<ChildTabBean?>(null)
    val childTabStateFlow: StateFlow<ChildTabBean?>
        get() = _mutableChildTabStateFlow.asStateFlow()

    fun getChildTabData(id: String) {
        viewModelScope.launch {
            try {
                val response = CommunityNet.childTabService.getChildTab(id)
                Log.d("responseId...", "getChildTabData: $response")
                _mutableChildTabStateFlow.emit(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}