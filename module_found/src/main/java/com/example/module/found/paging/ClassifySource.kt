package com.example.module.found.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lib_net.RetrofitClient
import com.example.module.found.bean.Item
import com.example.module.found.net.apiservice.ClassifyService

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/26 16:14
 */
class ClassifySource(private val id: String) : PagingSource<Int, Item>() {
    private var nextPageUrl: String = ""
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            //页码未定义置为1
            val currentPage = params.key ?: 1

            //仓库层请求数据
            val detailData = if (nextPageUrl == "")
                RetrofitClient.getService(ClassifyService::class.java)
                    .getClassifyDetail(id, "", "", "")
            else RetrofitClient.getService(ClassifyService::class.java)
                .getClassifyDetail(
                    id,
                    nextPageUrl.split("&")[0].split("=")[1],
                    nextPageUrl.split("&")[1].split("=")[1],
                    nextPageUrl.split("&")[2].split("=")[1]
                )
            nextPageUrl = detailData.nextPageUrl ?: ""

            val prevKey = if (currentPage > 1) currentPage - 1 else null
            val nextKey = if (nextPageUrl != "") currentPage + 1 else null

            LoadResult.Page(
                data = detailData.itemList,
                prevKey,
                nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}