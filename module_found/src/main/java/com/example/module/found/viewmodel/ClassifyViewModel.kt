package com.example.module.found.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.found.bean.ClassifyBean
import com.example.module.found.bean.ClassifyDetail
import com.example.module.found.net.FoundNet
import com.example.module.found.net.FoundNet.classifyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClassifyViewModel : ViewModel() {
    private var _mutableClassifyStateFlow = MutableStateFlow<List<ClassifyBean>?>(null)
    val classifyStateFlow: StateFlow<List<ClassifyBean>?>
        get() = _mutableClassifyStateFlow.asStateFlow()

    private var _mutableDetailStateFlow = MutableStateFlow<ClassifyDetail?>(null)
    val detailStateFlow: StateFlow<ClassifyDetail?>
        get() = _mutableDetailStateFlow.asStateFlow()

    fun getClassifyData() {
        viewModelScope.launch {
            try {
                val response = classifyService.getClassify()
                _mutableClassifyStateFlow.emit(response)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getClassifyDetailData(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = classifyService.getClassifyDetail(id,"","","")
                Log.d("response", "成功获取数据$response")
                _mutableDetailStateFlow.emit(response)

            } catch (e: Exception) {
                Log.e("enq", "getClassifyDetailData: 出错了...", e)
            }
        }
    }
}