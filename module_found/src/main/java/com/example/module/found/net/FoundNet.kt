package com.example.module.found.net

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
    private const val BASE_URL = "https://baobab.kaiyanapp.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val classifyService: ClassifyService = retrofit.create(ClassifyService::class.java)
    val specialService: SpecialService = retrofit.create(SpecialService::class.java)
}