package com.example.module.community.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib.base.NetStatus
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.net.CommunityRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/22 19:18
 */
class ChildTabViewModel : ViewModel() {
    private var _mutableChildTabStateFlow = MutableStateFlow<List<ChildTabBean>?>(null)
    val childTabStateFlow: StateFlow<List<ChildTabBean>?>
        get() = _mutableChildTabStateFlow.asStateFlow()

    private var _LoadStatus = MutableLiveData<NetStatus>()
    val loadStatus: MutableLiveData<NetStatus>
        get() = _LoadStatus

    fun getChildTabData() {
        _LoadStatus.value = NetStatus.LOADING

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val idList = CommunityRepo.tabService.getCommunityTab().tabInfo.tabList.map {
                    it.id.toString()
                }

                Log.d("idListZeq", "getChildTabData: $idList")
                val childList = mutableListOf<ChildTabBean>()
                for (id in idList) {
                    val response: ChildTabBean = if (id == "-1") {
                        CommunityRepo.childTabService.getChildTab("0", "true", "", "")

                    } else {
                        CommunityRepo.childTabService.getChildTab(id, "", "", "")
                    }

                    Log.d("Zeq", "getChildTabData: $response")
                    childList.add(response)
                }
                _LoadStatus.postValue(NetStatus.SUCCESS)

                _mutableChildTabStateFlow.emit(childList)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}