package com.example.module.community.net.apiservice

import com.example.module.community.bean.TabListBean
import retrofit2.http.GET

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:33
 */
interface TabService {
    @GET("api/v7/tag/tabList")
    suspend fun getCommunityTab(): TabListBean
}