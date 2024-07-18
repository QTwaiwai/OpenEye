package com.example.lib_net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * description : 网络请求的工具
 * author : QTwawa
 * date : 2024/7/16 15:13
 */
object RetrofitClient {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://baobab.kaiyanapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    fun <T> getService(service: Class<T>): T {
        return retrofit.build().create(service)
    }

}