package com.example.module.home.service

import com.example.module.home.bean.DailyRvData
import com.example.module.home.bean.RecommendData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * description : 日报Rv的服务
 * author : QTwawa
 * date : 2024/7/17 19:01
 */
interface DailyRvService {
    @GET("api/v5/index/tab/feed")
    fun getDailyRvData(): Observable<DailyRvData>

    @GET
    fun getNextDailyRvData(@Url url: String): Observable<DailyRvData>
}