package com.example.module.found.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.net.FoundNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:38
 */
class SpecialViewModel : ViewModel() {
    private var _mutableSpecialAllStateFlow = MutableStateFlow<List<SpecialDetailBean>?>(null)
    val specialAllStateFlow: StateFlow<List<SpecialDetailBean>?>
        get() = _mutableSpecialAllStateFlow.asStateFlow()

    private var _mutableSpecialStateFlow = MutableStateFlow<SpecialDetailBean?>(null)
    val specialStateFlow: StateFlow<SpecialDetailBean?>
        get() = _mutableSpecialStateFlow.asStateFlow()

    fun getSpecialAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseId = FoundNet.specialService.getSpecial().itemList.map {
                    it.data.id.toString()
                }
                Log.d("responseId", "getSpecialData: $responseId")

                val response = mutableListOf<SpecialDetailBean>()
                for (id in responseId) {
                    val responseItem = FoundNet.specialService.getSpecialDetail(id)

                    response.add(responseItem)
                }
                _mutableSpecialAllStateFlow.emit(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSpecialData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseItem = FoundNet.specialService.getSpecialDetail(id)

                _mutableSpecialStateFlow.emit(responseItem)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}