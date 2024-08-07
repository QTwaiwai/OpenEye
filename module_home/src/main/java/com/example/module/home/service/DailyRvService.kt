package com.example.module.home.service

import com.example.module.home.bean.DailyRvData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * description : 日报Rv的服务
 * author : QTwawa
 * date : 2024/7/25 17:06
 */
interface DailyRvService {
    @GET("api/v5/index/tab/allRec")
    suspend fun getDailyRvData(
        @Query("page") page:String,
        @Query("isTag") isTag:String,
        @Query("adIndex")adIndex:String,): DailyRvData
}