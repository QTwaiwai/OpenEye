package com.example.module.found.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.found.bean.Classify
import com.example.module.found.bean.ClassifyDetail
import com.example.module.found.net.ClassifyRepo
import com.example.module.found.net.ClassifyRepo.classifyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClassifyViewModel() : ViewModel() {
    private var _mutableClassifyStateFlow = MutableStateFlow<List<Classify>?>(null)
    val classifyStateFlow: StateFlow<List<Classify>?>
        get() = _mutableClassifyStateFlow.asStateFlow()

    private var _mutableDetailStateFlow = MutableStateFlow<ClassifyDetail?>(null)
    val detailStateFlow: StateFlow<ClassifyDetail?>
        get() = _mutableDetailStateFlow.asStateFlow()

    fun getClassifyData() {
        viewModelScope.launch {
            try {
                val response = ClassifyRepo.classifyService.getClassify()
                _mutableClassifyStateFlow.emit(response)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getClassifyDetailData(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                /*val response = retryRequest { classifyService.getClassifyDetail(id) }
                emitResponse(response)*/
                val response = classifyService.getClassifyDetail(id)
                Log.d("response", "成功获取数据$response")
                _mutableDetailStateFlow.emit(response)

            } catch (e: Exception) {
                Log.e("enq", "getClassifyDetailData: 出错了...", e)
                //emitError(e)
            }
        }
    }

    /*private suspend fun retryRequest(block: suspend () -> ClassifyDetail): ClassifyDetail {
        var attempts = 0
        while (true) {
            try {
                return block()
            } catch (e: Exception) {
                attempts++
                if (attempts > 3) throw e // 超过3次重试后，重新抛出异常
                Log.e("enq", "重试请求, 尝试 $attempts", e)
                delay(1000) // 等待1秒后重试
            }
        }
    }*/

    private suspend fun emitResponse(response: ClassifyDetail) {
        Log.d("response", "成功获取数据$response")
        _mutableDetailStateFlow.emit(response)
    }

    private fun emitError(error: Exception) {
        Log.e("error", "错误: ${error.message}")
    }
}