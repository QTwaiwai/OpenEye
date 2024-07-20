package com.example.module.found.net

import com.example.lib_net.RetrofitClient
import com.example.module.found.net.apiservice.ClassifyService
import com.example.module.found.net.apiservice.SpecialService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/16 21:07
 */
object FoundNet {
    val classifyService: ClassifyService = RetrofitClient.getService(ClassifyService::class.java)

    val specialService: SpecialService = RetrofitClient.getService(SpecialService::class.java)
}