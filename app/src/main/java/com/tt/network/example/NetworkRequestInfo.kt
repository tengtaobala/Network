package com.tt.network.example

import android.app.Application
import android.content.Context
import com.tt.lib.network.INetworkRequestInfo
import com.tt.network.example.interceptor.HandleErrorInterceptor
import com.tt.network.example.interceptor.HandleWanInterceptor
import com.tt.network.example.service.BoXingTongService
import okhttp3.Interceptor
import java.util.HashMap

class NetworkRequestInfo : INetworkRequestInfo {
    override fun getContext(): Context = App.CONTEXT

    override fun useNetCache(): Boolean = true

    override fun requestHeaderMap(): HashMap<String, String>? = null

    override val isDebug = BuildConfig.DEBUG

    override fun interceptors(list:MutableList<Interceptor>) {
//      list.add(HandleErrorInterceptor())
      list.add(HandleWanInterceptor())

    }

    override fun networkInterceptor(list:MutableList<Interceptor>){}

    override val connectTimeout = 0
    override val readTimeout = 0
    override val writeTimeout = 0

    override val baseUrl: String = BoXingTongService.BASE_URL
}