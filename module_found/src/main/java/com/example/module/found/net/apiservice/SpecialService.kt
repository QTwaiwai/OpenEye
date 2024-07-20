package com.example.module.found.net.apiservice

import com.example.module.found.bean.SpecialBean
import com.example.module.found.bean.SpecialDetailBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:30
 */
interface SpecialService {
    @GET("api/v3/specialTopics")
    suspend fun getSpecial(): SpecialBean

    @GET("api/v3/lightTopics/internal/{id}")
    suspend fun getSpecialDetail(@Path("id") id: String): SpecialDetailBean
}