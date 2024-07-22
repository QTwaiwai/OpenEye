package com.example.module.community.net.apiservice

import android.database.Observable
import com.example.module.community.bean.ChildTabBean
import com.example.module.community.bean.Item
import com.example.module.community.bean.TabListBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:33
 */
interface TabService {
    @GET("api/v7/tag/tabList")
    suspend fun getCommunityTab(): TabListBean

    /*@GET
    suspend fun getVpData(@Url url: String): Observable<ChildTabBean>*/
}