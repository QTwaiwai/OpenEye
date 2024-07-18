package com.example.module.found.bean

import com.google.gson.annotations.SerializedName

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 16:35
 */
data class SpecialDetailBean(
    @SerializedName("adTrack")
    val adTrack: Any, // null
    @SerializedName("brief")
    val brief: String, // 6 支戛纳获奖广告，不容错过的创意
    @SerializedName("count")
    val count: Int, // 6
    @SerializedName("headerImage")
    val headerImage: String, // http://ali-img.kaiyanapp.com/b90c472fbf2262ad28fde40bef8ed349.jpeg?imageMogr2/quality/60/format/jpg
    @SerializedName("id")
    val id: Int, // 746
    @SerializedName("itemList")
    val itemList: List<Item>,
    @SerializedName("shareLink")
    val shareLink: String, // http://www.eyepetizer.net/videos_article.html?nid=746&shareable=true
    @SerializedName("text")
    val text: String // 戛纳国际创意节被誉为「广告界的奥斯卡」，是全球传播、广告和创意界的年度盛事。开眼为你精选了 6 支戛纳创意节获奖广告作品，透过那一座座沉甸甸的奖杯，回归到作品本身，感受创意人们的灵感火花吧！
) {
    data class Item(
        @SerializedName("adIndex")
        val adIndex: Int, // -1
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("id")
        val id: Int, // 318772
        @SerializedName("tag")
        val tag: Any, // null
        @SerializedName("trackingData")
        val trackingData: Any, // null
        @SerializedName("type")
        val type: String // autoPlayFollowCard
    ) {
        data class Data(
            @SerializedName("adTrack")
            val adTrack: List<Any>,
            @SerializedName("content")
            val content: Content,
            @SerializedName("dataType")
            val dataType: String, // FollowCard
            @SerializedName("header")
            val header: Header
        ) {
            data class Content(
                @SerializedName("adIndex")
                val adIndex: Int, // -1
                @SerializedName("data")
                val `data`: Data,
                @SerializedName("id")
                val id: Int, // 0
                @SerializedName("tag")
                val tag: Any, // null
                @SerializedName("trackingData")
                val trackingData: Any, // null
                @SerializedName("type")
                val type: String // video
            ) {
                data class Data(
                    @SerializedName("ad")
                    val ad: Boolean, // false
                    @SerializedName("adTrack")
                    val adTrack: List<Any>,
                    @SerializedName("author")
                    val author: Author,
                    @SerializedName("brandWebsiteInfo")
                    val brandWebsiteInfo: Any, // null
                    @SerializedName("campaign")
                    val campaign: Any, // null
                    @SerializedName("category")
                    val category: String, // 广告
                    @SerializedName("collected")
                    val collected: Boolean, // false
                    @SerializedName("consumption")
                    val consumption: Consumption,
                    @SerializedName("cover")
                    val cover: Cover,
                    @SerializedName("dataType")
                    val dataType: String, // VideoBeanForClient
                    @SerializedName("date")
                    val date: Long, // 1688950833000
                    @SerializedName("description")
                    val description: String, // 厌倦了收到无休止的演示文稿而没有任何解决方案？摆脱陈旧的咨询废话，迎接业务和数字化转型的未来。Golbant 是一家 IT 和软件开发公司，公司的使命是提供有价值的信息，帮助组织完成转型的过程，热衷于提供见解和建议以使客户能够满足用户的期望。此广告荣获 2023 年戛纳国际创意节银狮奖。 From Globant
                    @SerializedName("descriptionEditor")
                    val descriptionEditor: String, // 厌倦了收到无休止的演示文稿而没有任何解决方案？摆脱陈旧的咨询废话，迎接业务和数字化转型的未来。Golbant 是一家 IT 和软件开发公司，公司的使命是提供有价值的信息，帮助组织完成转型的过程，热衷于提供见解和建议以使客户能够满足用户的期望。此广告荣获 2023 年戛纳国际创意节银狮奖。 From Globant
                    @SerializedName("descriptionPgc")
                    val descriptionPgc: String,
                    @SerializedName("duration")
                    val duration: Int, // 48
                    @SerializedName("favoriteAdTrack")
                    val favoriteAdTrack: Any, // null
                    @SerializedName("id")
                    val id: Int, // 318772
                    @SerializedName("idx")
                    val idx: Int, // 0
                    @SerializedName("ifLimitVideo")
                    val ifLimitVideo: Boolean, // false
                    @SerializedName("label")
                    val label: Any, // null
                    @SerializedName("labelList")
                    val labelList: List<Any>,
                    @SerializedName("lastViewTime")
                    val lastViewTime: Any, // null
                    @SerializedName("library")
                    val library: String, // DAILY
                    @SerializedName("playInfo")
                    val playInfo: List<PlayInfo>,
                    @SerializedName("playUrl")
                    val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=318772&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=
                    @SerializedName("played")
                    val played: Boolean, // false
                    @SerializedName("playlists")
                    val playlists: Any, // null
                    @SerializedName("promotion")
                    val promotion: Any, // null
                    @SerializedName("provider")
                    val provider: Provider,
                    @SerializedName("reallyCollected")
                    val reallyCollected: Boolean, // false
                    @SerializedName("recallSource")
                    val recallSource: Any, // null
                    @SerializedName("releaseTime")
                    val releaseTime: Long, // 1688950833000
                    @SerializedName("remark")
                    val remark: String, // https://www.xinpianchang.com/a12593105?from=ArticleList
                    @SerializedName("resourceType")
                    val resourceType: String, // video
                    @SerializedName("searchWeight")
                    val searchWeight: Int, // 0
                    @SerializedName("shareAdTrack")
                    val shareAdTrack: Any, // null
                    @SerializedName("slogan")
                    val slogan: String,
                    @SerializedName("src")
                    val src: Any, // null
                    @SerializedName("subtitles")
                    val subtitles: List<Any>,
                    @SerializedName("tags")
                    val tags: List<Tag>,
                    @SerializedName("thumbPlayUrl")
                    val thumbPlayUrl: Any, // null
                    @SerializedName("title")
                    val title: String, // 1000页PPT？我要被同事卷死了！
                    @SerializedName("titlePgc")
                    val titlePgc: String, // 广告f
                    @SerializedName("type")
                    val type: String, // NORMAL
                    @SerializedName("videoPosterBean")
                    val videoPosterBean: VideoPosterBean,
                    @SerializedName("waterMarks")
                    val waterMarks: Any, // null
                    @SerializedName("webAdTrack")
                    val webAdTrack: Any, // null
                    @SerializedName("webUrl")
                    val webUrl: WebUrl
                ) {
                    data class Author(
                        @SerializedName("adTrack")
                        val adTrack: Any, // null
                        @SerializedName("approvedNotReadyVideoCount")
                        val approvedNotReadyVideoCount: Int, // 0
                        @SerializedName("description")
                        val description: String, // 我们精选世界最好看的广告，为全世界广告人的精彩创意点赞。
                        @SerializedName("expert")
                        val expert: Boolean, // false
                        @SerializedName("follow")
                        val follow: Follow,
                        @SerializedName("icon")
                        val icon: String, // http://ali-img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
                        @SerializedName("id")
                        val id: Int, // 2162
                        @SerializedName("ifPgc")
                        val ifPgc: Boolean, // true
                        @SerializedName("latestReleaseTime")
                        val latestReleaseTime: Long, // 1690074015000
                        @SerializedName("link")
                        val link: String,
                        @SerializedName("name")
                        val name: String, // 全球广告精选
                        @SerializedName("recSort")
                        val recSort: Int, // 0
                        @SerializedName("shield")
                        val shield: Shield,
                        @SerializedName("videoNum")
                        val videoNum: Int // 2862
                    ) {
                        data class Follow(
                            @SerializedName("followed")
                            val followed: Boolean, // false
                            @SerializedName("itemId")
                            val itemId: Int, // 2162
                            @SerializedName("itemType")
                            val itemType: String // author
                        )

                        data class Shield(
                            @SerializedName("itemId")
                            val itemId: Int, // 2162
                            @SerializedName("itemType")
                            val itemType: String, // author
                            @SerializedName("shielded")
                            val shielded: Boolean // false
                        )
                    }

                    data class Consumption(
                        @SerializedName("collectionCount")
                        val collectionCount: Int, // 549
                        @SerializedName("realCollectionCount")
                        val realCollectionCount: Int, // 452
                        @SerializedName("replyCount")
                        val replyCount: Int, // 7
                        @SerializedName("shareCount")
                        val shareCount: Int // 77
                    )

                    data class Cover(
                        @SerializedName("blurred")
                        val blurred: String, // http://ali-img.kaiyanapp.com/4154688feb2ec02d940172f155918d57.jpeg?imageMogr2/quality/60/format/jpg
                        @SerializedName("detail")
                        val detail: String, // http://ali-img.kaiyanapp.com/c0d009dbfc32e33b5d2bc667cd948ad2.jpeg?imageMogr2/quality/60/format/jpg
                        @SerializedName("feed")
                        val feed: String, // http://ali-img.kaiyanapp.com/c0d009dbfc32e33b5d2bc667cd948ad2.jpeg?imageMogr2/quality/60/format/jpg
                        @SerializedName("homepage")
                        val homepage: String, // http://img.kaiyanapp.com/c0d009dbfc32e33b5d2bc667cd948ad2.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                        @SerializedName("sharing")
                        val sharing: Any // null
                    )

                    data class PlayInfo(
                        @SerializedName("height")
                        val height: Int, // 720
                        @SerializedName("name")
                        val name: String, // 高清
                        @SerializedName("type")
                        val type: String, // high
                        @SerializedName("url")
                        val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=318772&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                        @SerializedName("urlList")
                        val urlList: List<Url>,
                        @SerializedName("width")
                        val width: Int // 1280
                    ) {
                        data class Url(
                            @SerializedName("name")
                            val name: String, // aliyun
                            @SerializedName("size")
                            val size: Int, // 4160239
                            @SerializedName("url")
                            val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=318772&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                        )
                    }

                    data class Provider(
                        @SerializedName("alias")
                        val alias: String, // youtube
                        @SerializedName("icon")
                        val icon: String, // http://ali-img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                        @SerializedName("name")
                        val name: String // YouTube
                    )

                    data class Tag(
                        @SerializedName("actionUrl")
                        val actionUrl: String, // eyepetizer://tag/748/?title=%E8%BF%99%E4%BA%9B%E5%B9%BF%E5%91%8A%E8%B6%85%E6%9C%89%E6%A2%97
                        @SerializedName("adTrack")
                        val adTrack: Any, // null
                        @SerializedName("bgPicture")
                        val bgPicture: String, // http://img.kaiyanapp.com/9056413cfeffaf0c841d894390aa8e08.jpeg?imageMogr2/quality/60/format/jpg
                        @SerializedName("childTagIdList")
                        val childTagIdList: Any, // null
                        @SerializedName("childTagList")
                        val childTagList: Any, // null
                        @SerializedName("communityIndex")
                        val communityIndex: Int, // 0
                        @SerializedName("desc")
                        val desc: String, // 哈哈哈哈哈哈哈哈
                        @SerializedName("haveReward")
                        val haveReward: Boolean, // false
                        @SerializedName("headerImage")
                        val headerImage: String, // http://img.kaiyanapp.com/ff0f6d0ad5f4b6211a3f746aaaffd916.jpeg?imageMogr2/quality/60/format/jpg
                        @SerializedName("id")
                        val id: Int, // 748
                        @SerializedName("ifNewest")
                        val ifNewest: Boolean, // false
                        @SerializedName("name")
                        val name: String, // 这些广告超有梗
                        @SerializedName("newestEndTime")
                        val newestEndTime: Any, // null
                        @SerializedName("tagRecType")
                        val tagRecType: String // IMPORTANT
                    )

                    data class VideoPosterBean(
                        @SerializedName("fileSizeStr")
                        val fileSizeStr: String, // 3.22MB
                        @SerializedName("scale")
                        val scale: Double, // 0.725
                        @SerializedName("url")
                        val url: String // http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/12648478ea018580c6276db02ceb969c.mp4
                    )

                    data class WebUrl(
                        @SerializedName("forWeibo")
                        val forWeibo: String, // https://m.eyepetizer.net/u1/video-detail?video_id=318772&resource_type=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
                        @SerializedName("raw")
                        val raw: String // http://www.eyepetizer.net/detail.html?vid=318772
                    )
                }
            }

            data class Header(
                @SerializedName("actionUrl")
                val actionUrl: String, // eyepetizer://pgc/detail/2162/?title=%E5%85%A8%E7%90%83%E5%B9%BF%E5%91%8A%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
                @SerializedName("followType")
                val followType: String, // author
                @SerializedName("icon")
                val icon: String, // http://ali-img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
                @SerializedName("iconType")
                val iconType: String, // round
                @SerializedName("id")
                val id: Int, // 318772
                @SerializedName("issuerName")
                val issuerName: String, // 全球广告精选
                @SerializedName("labelList")
                val labelList: Any, // null
                @SerializedName("showHateVideo")
                val showHateVideo: Boolean, // false
                @SerializedName("tagId")
                val tagId: Int, // 0
                @SerializedName("tagName")
                val tagName: Any, // null
                @SerializedName("time")
                val time: Long, // 1688950833000
                @SerializedName("topShow")
                val topShow: Boolean // false
            )
        }
    }
}