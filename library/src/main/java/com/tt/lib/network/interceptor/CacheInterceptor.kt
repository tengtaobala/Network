package com.tt.lib.network.interceptors

import android.content.Context
import android.net.ConnectivityManager


import com.tt.lib.network.INetworkRequestInfo
import okhttp3.CacheControl

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response


class CacheInterceptor(private val networkRequestInfo: INetworkRequestInfo) : Interceptor {

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!isNetworkAvailable(networkRequestInfo.getContext())) {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (!networkRequestInfo.useNetCache()) {
            return response
        }
        return if (!isNetworkAvailable(networkRequestInfo.getContext())) {
            val maxAge = 60 * 60
            response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        } else {
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
    }
}