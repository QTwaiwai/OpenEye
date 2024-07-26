package com.example.module.community.net.apiservice

import com.example.module.community.bean.ChildTabBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:34
 */
interface ChildTabService {
    @GET("api/v7/tag/childTab/{id}")
    suspend fun getChildTab(
        @Path("id") id: String,
        @Query("isRecTab") isRecTab: String,
        @Query("start") start: String,
        @Query("num") num: String
    ): ChildTabBean

    /*@GET
    suspend fun getMore(
        @Path("url") url: String
    ): ChildTabBean*/
}