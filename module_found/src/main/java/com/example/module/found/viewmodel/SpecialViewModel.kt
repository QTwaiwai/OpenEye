package com.example.module.found.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.found.bean.SpecialDetailBean
import com.example.module.found.net.FoundNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:38
 */
class SpecialViewModel : ViewModel() {
    private var _mutableSpecialStateFlow = MutableStateFlow<SpecialDetailBean?>(null)
    val specialStateFlow: StateFlow<SpecialDetailBean?>
        get() = _mutableSpecialStateFlow.asStateFlow()

    fun getSpecialData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = FoundNet.specialService.getSpecial()
                _mutableSpecialStateFlow.emit(response)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}