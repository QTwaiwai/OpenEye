package com.example.module.home.bean

/**
 * author : QTwawa
 * date : 2024/7/23 11:45
 */
data class DailyRvData(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<DrItem>,//1
    val nextPageUrl: String,
    val total: Int
)

data class DrItem(
    val adIndex: Int,
    val `data`: DrData,//2
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class DrData(
    val author: DrAuthor,
    val category: String,
    val consumption: DrConsumption,
    val cover: DrCover,
    val date: Long,
    val description: String,
    val duration: Int,
    val id: Int,
    val playUrl: String,
    val title: String,
)

data class DrAuthor(
    val adTrack: Any,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val videoNum: Int
)

data class DrConsumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
)



data class DrCover(
    val blurred: String,
    val detail: String,
    val feed: String,
    val homepage: Any,
    val sharing: Any
)