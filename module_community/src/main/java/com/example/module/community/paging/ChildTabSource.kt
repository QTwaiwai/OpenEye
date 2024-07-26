package com.example.module.community.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.module.community.bean.Item
import com.example.module.community.net.CommunityNet

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/26 12:18
 */
class ChildTabSource(private val id: String) : PagingSource<Int, Item>() {
    private var nextPageUrl = ""
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            //页码未定义置为1
            val currentPage = params.key ?: 1

            //仓库层请求数据
            val childTabData = if (nextPageUrl == "") {
                if (id == "0") {
                    CommunityNet.childTabService.getChildTab(id, "true", "", "")
                } else {
                    CommunityNet.childTabService.getChildTab(id, "", "", "")
                }
            } else {
                val parts = nextPageUrl.split("?")

                val queryParameters = parts[1].split("&")
                val start = queryParameters.firstOrNull { it.startsWith("start=") }
                    ?.substringAfter("start=").toString()
                val num = queryParameters.firstOrNull { it.startsWith("num=") }
                    ?.substringAfter("num=").toString()
                CommunityNet.childTabService.getChildTab(id, "", start, num)
            }

            nextPageUrl = childTabData.nextPageUrl ?: ""

            val prevKey = if (currentPage > 1) currentPage - 1 else null
            val nextKey = if (nextPageUrl != "") currentPage + 1 else null
            LoadResult.Page(
                data = childTabData.itemList,
                prevKey,
                nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}