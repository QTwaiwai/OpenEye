package com.example.module.community.net

import com.example.lib_net.RetrofitClient
import com.example.module.community.net.apiservice.ChildTabService
import com.example.module.community.net.apiservice.TabService

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/20 11:34
 */
object CommunityNet {
    val tabService: TabService = RetrofitClient.getService(TabService::class.java)

    val childTabService: ChildTabService = RetrofitClient.getService(ChildTabService::class.java)
}