package com.example.module.community.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.Item
import com.example.module.community.net.CommunityNet
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

    fun getChildTabData() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val idList = CommunityNet.tabService.getCommunityTab().tabInfo.tabList.map {
                    it.id.toString()
                }

                Log.d("idListZeq", "getChildTabData: $idList")
                val childList = mutableListOf<ChildTabBean>()
                for (id in idList) {
                    val response: ChildTabBean = if (id == "-1") {
                        CommunityNet.childTabService.getChildTab("0", "true", "", "")

                    } else {
                        CommunityNet.childTabService.getChildTab(id, "", "", "")
                    }

                    Log.d("Zeq", "getChildTabData: $response")
                    childList.add(response)
                }

                _mutableChildTabStateFlow.emit(childList)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}