package com.example.module.community.bean

import java.io.Serializable

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/19 22:16
 */
data class ChildTabBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String?,
    val total: Int
) : Serializable

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
) : Serializable

data class Data(
    val actionUrl: String,
    val adTrack: Any,
    val dataType: String,
    val description: String,
    val expert: Boolean,
    val follow: Follow,
    val haveReward: Boolean,
    val icon: String,
    val iconType: String,
    val id: Int,
    val ifNewest: Boolean,
    val ifPgc: Boolean,
    val ifShowNotificationIcon: Boolean,
    val medalIcon: Boolean,
    val newestEndTime: Any,
    val subTitle: Any,
    val switchStatus: Boolean,
    val title: String,
    val uid: Int
)

data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)