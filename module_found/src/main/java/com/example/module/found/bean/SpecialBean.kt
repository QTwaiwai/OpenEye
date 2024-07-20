package com.example.module.found.bean

import com.google.gson.annotations.SerializedName

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/18 18:10
 */
data class SpecialBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String,
    val total: Int
) {
    data class Item(
        val adIndex: Int,
        val `data`: Data,
        val id: Int,
        val tag: Any,
        val trackingData: Any,
        val type: String
    ) {
        data class Data(
            val actionUrl: String,
            val adTrack: List<Any>,
            val autoPlay: Boolean,
            val dataType: String,
            val description: String,
            val header: Any,
            val id: Int,
            val image: String,
            val label: Label,
            val labelList: List<Any>,
            val shade: Boolean,
            val title: String
        ) {
            data class Label(
                val card: String,
                val detail: Any,
                val text: String
            )
        }
    }
}
