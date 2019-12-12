package com.tt.lib.network


import android.app.Application
import android.content.Context
import java.util.HashMap

import okhttp3.Interceptor

interface INetworkRequestInfo {
    fun requestHeaderMap(): HashMap<String, String>?

    fun getContext(): Context

    val isDebug: Boolean

    fun interceptors(list:MutableList<Interceptor>)

    fun networkInterceptor(list:MutableList<Interceptor>)
    /**
     * 使用网络缓存
     */
    fun useNetCache(): Boolean

    /**
     * TimeUnit.MILLISECONDS
     *
     * @return
     */
    val connectTimeout: Int

    /**
     * TimeUnit.MILLISECONDS
     *
     * @return
     */
    val readTimeout: Int

    /**
     * TimeUnit.MILLISECONDS
     *
     * @return
     */
    val writeTimeout: Int


    /**
     * 请求url
     */
    val baseUrl: String

}
