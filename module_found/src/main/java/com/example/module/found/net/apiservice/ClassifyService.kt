package com.example.module.found.net.apiservice

import com.example.module.found.bean.ClassifyBean
import com.example.module.found.bean.ClassifyDetail
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/16 19:46
 */
interface ClassifyService {
    @GET("api/v4/categories")
    suspend fun getClassify(): List<ClassifyBean>

    @GET("api/v1/tag/videos")
    suspend fun getClassifyDetail(
        @Query("id") id: String,
        @Query("start") start: String,
        @Query("num") num: String,
        @Query("strategy") strategy: String
    ): ClassifyDetail
}