package com.example.module.community.bean

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/19 22:15
 */
data class TabListBean(
    val tabInfo: TabInfo
)

data class TabInfo(
    val defaultIdx: Int,
    val tabList: List<Tab>
)

data class Tab(
    val adTrack: Any,
    val apiUrl: String,
    val id: Int,
    val name: String,
    val nameType: Int,
    val tabType: Int
)