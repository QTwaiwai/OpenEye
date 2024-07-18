package com.example.module.home.bean

/**
 * description : 日报Rv的数据
 * author : QTwawa
 * date : 2024/7/17 16:36
 */
data class DailyRvData(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<DrItem>, //1
    val nextPageUrl: String,
    val total: Int
)

data class DrItem(
    val adIndex: Int,
    val `data`: DrData, //2
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String    //过滤TextCard
)

data class DrData(
    val actionUrl: Any,
    val adTrack: List<Any>,
    val content: DrContent, //3
    val dataType: String,
    val follow: Any,
    val header: DrHeader,
    val id: Int,
    val subTitle: Any,
    val text: String,
    val type: String
)

data class DrContent(
    val adIndex: Int,
    val `data`: DrDataX, //4
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class DrHeader(
    val actionUrl: String,
    val cover: Any,
    val description: String,
    val font: Any,
    val icon: String,
    val iconType: String,
    val id: Int,
    val label: Any,
    val labelList: Any,
    val rightText: Any,
    val showHateVideo: Boolean,
    val subTitle: Any,
    val subTitleFont: Any,
    val textAlign: String,
    val time: Long,
    val title: String   //作者名字
)

data class DrDataX(
    val ad: Boolean,
    val adTrack: List<Any>,
    val author: DrAuthor,
    val brandWebsiteInfo: Any,
    val campaign: Any,
    val category: String,
    val collected: Boolean,
    val consumption: DrConsumption,
    val cover: DrCover,   //feed为封面
    val dataType: String,
    val date: Long,
    val description: String, //"来自北爱尔兰的声音工作室 DEMON OFFICIAL 与视觉艺术家 Enea Colombi 合作，共同制作了他们的品牌宣传片：在一片布满伤痕的土地上，一匹忠诚的骏马永远等待着它的骑士的归来。这支短片为观众呈现了一部超震撼的视听盛宴。From Enea Colombi"
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
    val playInfo: List<DrPlayInfo>,
    val playUrl: String,
    val played: Boolean,
    val playlists: Any,
    val promotion: Any,
    val provider: DrProvider,
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
    val tags: List<DrTag>,
    val thumbPlayUrl: String,
    val title: String, //"亘古回响，超震撼视听盛宴「白马」"
    val titlePgc: String,   //封面标签
    val type: String,
    val videoPosterBean: Any,
    val waterMarks: Any,
    val webAdTrack: Any,
    val webUrl: DrWebUrl
)

data class DrAuthor(
    val adTrack: Any,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow:DrFollow,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: DrShield,
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
    val homepage: String,
    val sharing: Any
)

data class DrPlayInfo(
    val height: Int,
    val name: String,
    val type: String,
    val url: String,
    val urlList: List<DrUrl>,
    val width: Int
)

data class DrProvider(
    val alias: String,
    val icon: String,
    val name: String
)

data class DrTag(
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
    val newestEndTime: Any,
    val tagRecType: String
)

data class DrWebUrl(
    val forWeibo: String,
    val raw: String
)

data class DrFollow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)

data class DrShield(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
)

data class DrUrl(
    val name: String,
    val size: Int,
    val url: String
)