package com.example.module.home.service

import com.example.module.home.bean.RecommendData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * description : RecommendService
 * author : QTwawa
 * date : 2024/7/24 15:43
 */
interface RecommendService {
    @GET("api/v7/community/tab/rec")
    suspend fun getRecommend(
        @Query("startScore") page:String,
        @Query("pageCount") isTag:String,
    ): RecommendData

}