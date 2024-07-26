package com.example.module.home.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lib_net.RetrofitClient
import com.example.module.home.bean.Rec
import com.example.module.home.service.RecommendService

/**
 * author : QTwawa
 * date : 2024/7/24 11:08
 */
class RecommendSource():PagingSource<Int, Rec>() {
    private var nextParams = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Rec> {
        return try {
            val page = params.key ?: 1
            val homeData = if (nextParams == "")
                RetrofitClient.getService(RecommendService::class.java).getRecommend("","")
            else RetrofitClient.getService(RecommendService::class.java)
                .getRecommend(
                    nextParams.split("&")[0].split("=")[1],
                    nextParams.split("&")[1].split("=")[1]
                )
            nextParams = homeData.nextPageUrl.substring(
                53,
                homeData.nextPageUrl.length
            )

            val realData = mutableListOf<Rec>()
            for (data in homeData.itemList) {
                if (data.type == "communityColumnsCard"&&data.data.content.type=="ugcPicture") {
                    data.data.content.data.run {
                        realData.add(
                            Rec(
                                cover.feed,
                                description,
                                owner.avatar,
                                owner.nickname,
                                if (resourceType == "ugc_picture") urls else null,
                                resourceType,
                                if (resourceType == "ugc_video") playUrl else null
                            )
                        )
                    }
                }
            }
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (nextParams != "") page + 1 else null
            LoadResult.Page(realData, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Rec>): Int? =null
}