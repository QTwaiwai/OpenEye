package com.example.module.found.bean

import com.google.gson.annotations.SerializedName

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 18:10
 */
data class SpecialBean(
    @SerializedName("adExist")
    val adExist: Boolean, // false
    @SerializedName("count")
    val count: Int, // 10
    @SerializedName("itemList")
    val itemList: List<Item>,
    @SerializedName("nextPageUrl")
    val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v3/specialTopics?start=10&num=10
    @SerializedName("total")
    val total: Int // 0
) {
    data class Item(
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
        val type: String // banner2
    ) {
        data class Data(
            @SerializedName("actionUrl")
            val actionUrl: String, // eyepetizer://webview/?title=6+%E6%94%AF%E6%88%9B%E7%BA%B3%E8%8E%B7%E5%A5%96%E5%B9%BF%E5%91%8A%EF%BC%8C%E4%B8%8D%E5%AE%B9%E9%94%99%E8%BF%87%E7%9A%84%E5%88%9B%E6%84%8F&url=http%3A%2F%2Fwww.eyepetizer.net%2Fvideos_article.html%3Fnid%3D746%26shareable%3Dtrue
            @SerializedName("adTrack")
            val adTrack: List<Any>,
            @SerializedName("autoPlay")
            val autoPlay: Boolean, // false
            @SerializedName("dataType")
            val dataType: String, // Banner
            @SerializedName("description")
            val description: String,
            @SerializedName("header")
            val header: Any, // null
            @SerializedName("id")
            val id: Int, // 746
            @SerializedName("image")
            val image: String, // http://img.kaiyanapp.com/35f149197b45c60041404c8a072123b1.png?imageMogr2/quality/60/format/jpg
            @SerializedName("label")
            val label: Label,
            @SerializedName("labelList")
            val labelList: List<Any>,
            @SerializedName("shade")
            val shade: Boolean, // false
            @SerializedName("title")
            val title: String
        ) {
            data class Label(
                @SerializedName("card")
                val card: String,
                @SerializedName("detail")
                val detail: Any, // null
                @SerializedName("text")
                val text: String
            )
        }
    }
}
