package com.example.model_hot.net

import com.example.lib_net.RetrofitClient

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/25 12:05
 */
object HotNetRepo {

    val rankService: RankService = RetrofitClient.getService(RankService::class.java)

}