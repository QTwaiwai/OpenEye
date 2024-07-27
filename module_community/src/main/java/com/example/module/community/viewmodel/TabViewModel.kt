package com.example.module.community.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.lib.base.NetStatus
import com.example.module.community.bean.TabListBean
import com.example.module.community.net.CommunityRepo
import com.example.module.community.paging.ChildTabSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:49
 */
class TabViewModel : ViewModel() {

    private var _mutableTabStateFlow = MutableStateFlow<TabListBean?>(null)
    val tabStateFlow: StateFlow<TabListBean?>
        get() = _mutableTabStateFlow.asStateFlow()

    private var _LoadStatus = MutableLiveData<NetStatus>()
    val loadStatus: MutableLiveData<NetStatus>
        get() = _LoadStatus

    fun getTabData() {
        _LoadStatus.value = NetStatus.LOADING

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = CommunityRepo.tabService.getCommunityTab()
                Log.d("ZeqResponse", "getTabData: $response")

                _mutableTabStateFlow.emit(response)
                withContext(Dispatchers.Main) {
                    _LoadStatus.value = NetStatus.SUCCESS
                }
            } catch (e: Exception) {
                _LoadStatus.value = NetStatus.FAIL
                e.printStackTrace()
            }
        }
    }

    fun getChildTabData(id: String) = Pager(PagingConfig(pageSize = 20, 10)) {
        ChildTabSource(id)
    }.flow.cachedIn(viewModelScope)

}