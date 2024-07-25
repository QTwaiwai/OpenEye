package com.example.model_hot.net

import com.example.model_hot.bean.RankBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/25 11:58
 */
interface RankService {
    @GET("/api/v4/rankList/videos")
    fun getRankList(@Query("strategy") strategy: String): Observable<RankBean>
}