package com.example.module.community.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.Item
import com.example.module.community.bean.TabListBean
import com.example.module.community.net.CommunityNet
import com.example.module.community.paging.ChildTabSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
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

    fun getChildTabData(id: String) = Pager(PagingConfig(pageSize = 20, 10)) {
        ChildTabSource(id)
    }.flow.cachedIn(viewModelScope)

}