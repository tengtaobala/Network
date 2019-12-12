package com.tt.lib.network.interceptors

import android.text.TextUtils


import com.tt.lib.network.INetworkRequestInfo

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class RequestInterceptor(private val mNetworkRequestInfo: INetworkRequestInfo?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()

        mNetworkRequestInfo?.let {
            it.requestHeaderMap()?.let { map ->
                for (key in map.keys) {
                    if (!TextUtils.isEmpty(map[key])) {
                        builder.addHeader(key, map[key]!!)
                    }
                }
            }
        }

        return chain.proceed(builder.build())
    }
}