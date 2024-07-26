package com.example.module.found.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.module.found.bean.ClassifyBean
import com.example.module.found.bean.ClassifyDetail
import com.example.module.found.net.FoundNet
import com.example.module.found.net.FoundNet.classifyService
import com.example.module.found.paging.ClassifySource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClassifyViewModel : ViewModel() {
    private var _mutableClassifyStateFlow = MutableStateFlow<List<ClassifyBean>?>(null)
    val classifyStateFlow: StateFlow<List<ClassifyBean>?>
        get() = _mutableClassifyStateFlow.asStateFlow()

    fun getClassifyData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = classifyService.getClassify()
                _mutableClassifyStateFlow.emit(response)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getClassifyDetailData(id: String) = Pager(PagingConfig(pageSize = 20, 10)) {
        ClassifySource(id)
    }.flow.cachedIn(viewModelScope)
}