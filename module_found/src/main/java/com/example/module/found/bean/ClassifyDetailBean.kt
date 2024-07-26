package com.example.module.found.bean

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/17 20:04
 */
data class ClassifyDetail(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String?,
    val total: Int
)

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val type: String
)

data class Data(
    val adTrack: List<Any>,
    val content: Content,
    val header: Header,
    val dataType: String,
)
data class Header(
    val title:String ,
    val icon: String,
    val iconType: String,
    val description: String
)

data class Content(
    val adIndex: Int,
    val `data`: DataX,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class DataX(
    val ad: Boolean,
    val adTrack: List<Any>,
    val areaSet: List<Any>,
    val author: Author, // 作者
    val brandWebsiteInfo: Any,
    val campaign: Any,
    val candidateId: Int,
    val category: String,
    val collected: Boolean,
    val consumption: Consumption,
    val cover: Cover,
    val createTime: Long,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val descriptionPgc: String, //视频描述
    val duration: Int,
    val favoriteAdTrack: Any,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val infoStatus: String,
    val label: Any,
    val labelList: List<Any>,
    val lastViewTime: Any,
    val library: String,
    val playUrl: String, // 视频链接
    val played: Boolean,
    val playlists: Any,
    val premiereDate: Any,
    val promotion: Any,
    val reallyCollected: Boolean,
    val recallSource: Any,
    val recall_source: Any,
    val releaseTime: Long,
    val remark: String,
    val resourceType: String,//视频类型
    val searchWeight: Int,
    val shareAdTrack: Any,
    val showLabel: Boolean,
    val slogan: Any,
    val sourceUrl: String,
    val src: Any,
    val status: String,
    val subtitleStatus: String,
    val subtitles: List<Any>,
    val tags: List<Tag>,
    val tailTimePoint: Int,
    val thumbPlayUrl: Any,
    val title: String,
    val titlePgc: String, //视频标题
    val translateStatus: String,
    val type: String,
    val updateTime: Long,
    val videoPosterBean: VideoPosterBean,
    val waterMarks: Any,
    val webAdTrack: Any,
)

data class Cover(
    val cover: String,
    val feed: String,
    val detail: String,
    val blurred: String
)

data class Author(
    val adTrack: Any,
    val amplifiedLevelId: Int,
    val approvedNotReadyVideoCount: Int,
    val authorStatus: String,
    val authorType: String,
    val cover: String,
    val description: String,
    val expert: Boolean,
    val icon: String, //作者头像
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val library: String,
    val link: String,
    val name: String, //作者名字
    val pendingVideoCount: Int,
    val recSort: Int,
    val videoNum: Int
)

data class Consumption(
    val collectionCount: Int,
    val playCount: Int, //视频播放量
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
)

data class Tag(
    val actionUrl: String,
    val adTrack: Any,
    val alias: Any,
    val bgPicture: String,
    val childTagIdList: Any,
    val childTagList: Any,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val ifShow: Boolean,
    val level: Int,
    val name: String,
    val newestEndTime: Any,
    val parentId: Int,
    val recWeight: Double,
    val relationType: Int,
    val tagRecType: String,
    val tagStatus: String,
    val top: Int,
    val type: String
)

data class VideoPosterBean(
    val fileSizeStr: String,
    val scale: Double,
    val url: String
)
