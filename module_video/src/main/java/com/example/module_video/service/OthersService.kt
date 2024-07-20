package com.example.module_video.service

import com.example.module_video.bean.OthersData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * description : TODO:类的作用
 * author : QTwawa
 * date : 2024/7/18 23:02
 */
interface OthersService {
    @GET("api/v4/video/related?")
    fun getOthersData(@Query("id") id: Int): Observable<OthersData>
}