package com.example.module.home.service

import com.example.module.home.bean.DailyVp2Data
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * description : 请求的Banner的数据
 * author : QTwawa
 * date : 2024/7/18 10:05
 */
interface DailyVp2Service {
    @GET("api/v4/rankList/videos?strategy=monthly")
     fun getDailyVp2Data(): Observable<DailyVp2Data>
}