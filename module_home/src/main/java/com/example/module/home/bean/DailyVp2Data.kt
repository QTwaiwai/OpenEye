package com.example.module.home.bean

/**
 * description : 轮播栏的数据
 * author : QTwawa
 * date : 2024/7/18 09:58
 */
data class DailyVp2Data(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<DvItem>,
    val nextPageUrl: Any,
    val total: Int
)

data class DvItem(
    val adIndex: Int,
    val `data`: DvData,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class DvData(
    val ad: Boolean,
    val adTrack: List<Any>,
    val author: DvAuthor,
    val brandWebsiteInfo: Any,
    val campaign: Any,
    val category: String,
    val collected: Boolean,
    val consumption: DvConsumption,
    val cover: DvCover,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val descriptionPgc: String,
    val duration: Int,
    val favoriteAdTrack: Any,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val label: Any,
    val labelList: List<Any>,
    val lastViewTime: Any,
    val library: String,
    val playInfo: List<DvPlayInfo>,
    val playUrl: String,
    val played: Boolean,
    val playlists: Any,
    val promotion: Any,
    val provider: DvProvider,
    val reallyCollected: Boolean,
    val recallSource: Any,
    val recall_source: Any,
    val releaseTime: Long,
    val remark: String,
    val resourceType: String,
    val searchWeight: Int,
    val shareAdTrack: Any,
    val slogan: String,
    val src: Any,
    val subtitles: List<Any>,
    val tags: List<DvTag>,
    val thumbPlayUrl: String,
    val title: String,
    val titlePgc: String,
    val type: String,
    val videoPosterBean: DvVideoPosterBean,
    val waterMarks: Any,
    val webAdTrack: Any,
    val webUrl: DvWebUrl
)

data class DvAuthor(
    val adTrack: Any,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: DvFollow,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: DvShield,
    val videoNum: Int
)

data class DvConsumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
)

data class DvCover(
    val blurred: String,
    val detail: String,
    val feed: String,
    val homepage: String,
    val sharing: Any
)

data class DvPlayInfo(
    val height: Int,
    val name: String,
    val type: String,
    val url: String,
    val urlList: List<DvUrl>,
    val width: Int
)

data class DvProvider(
    val alias: String,
    val icon: String,
    val name: String
)

data class DvTag(
    val actionUrl: String,
    val adTrack: Any,
    val bgPicture: String,
    val childTagIdList: Any,
    val childTagList: Any,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val newestEndTime: Long,
    val tagRecType: String
)

data class DvVideoPosterBean(
    val fileSizeStr: String,
    val scale: Double,
    val url: String
)

data class DvWebUrl(
    val forWeibo: String,
    val raw: String
)

data class DvFollow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)

data class DvShield(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
)

data class DvUrl(
    val name: String,
    val size: Int,
    val url: String
)