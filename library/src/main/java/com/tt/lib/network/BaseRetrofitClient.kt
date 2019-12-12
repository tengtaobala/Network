package com.tt.lib.network

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.tt.lib.network.interceptor.ResponseInterceptor
import com.tt.lib.network.interceptors.CacheInterceptor
import com.tt.lib.network.interceptors.RequestInterceptor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

abstract class BaseRetrofitClient {
    companion object {
        private const val DEFAULT_CONNECT_TIME_OUT = 15 * 1000
    }


    fun <S> getService(serviceClass: Class<S>, networkRequestInfo: INetworkRequestInfo): S {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient(networkRequestInfo))
            .baseUrl(networkRequestInfo.baseUrl)
            .build()
            .create(serviceClass)
    }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)


    private fun getClient(networkRequestInfo: INetworkRequestInfo): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(getConnectTimeout(networkRequestInfo).toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(getReadTimeout(networkRequestInfo).toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(getWriteTimeout(networkRequestInfo).toLong(), TimeUnit.MILLISECONDS)


        /*可以统一添加网络参数到请求头*/
        builder.addInterceptor(RequestInterceptor(networkRequestInfo))

        /*网络请求返回的时候的数据处理*/
        builder.addInterceptor(ResponseInterceptor())

        addInterceptor(networkRequestInfo, builder)

        val logging = HttpLoggingInterceptor()
        //BODY打印信息,NONE不打印信息
        logging.level =
            if (networkRequestInfo.isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(logging)


        //设置cookie
        builder.cookieJar(
            PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(networkRequestInfo.getContext())
            )
        )

        val httpCacheDirectory = File(networkRequestInfo.getContext().cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
        builder.addInterceptor(CacheInterceptor(networkRequestInfo))

        handleBuilder(builder)

        val httpClient = builder.build()
        httpClient.dispatcher.maxRequestsPerHost = 20


        return httpClient
    }

    private fun addInterceptor(
        networkRequestInfo: INetworkRequestInfo,
        okHttpClient: OkHttpClient.Builder
    ) {
        val interceptors = mutableListOf<Interceptor>()
        networkRequestInfo.interceptors(interceptors)
        interceptors?.let {
            for (interceptor in interceptors) {
                okHttpClient.addInterceptor(interceptor)
            }
        }

        val networkInterceptors = mutableListOf<Interceptor>()
        networkRequestInfo.networkInterceptor(networkInterceptors)
        networkInterceptors?.let {
            for (interceptor in networkInterceptors) {
                okHttpClient.addNetworkInterceptor(interceptor)

            }
        }


    }


    private fun getConnectTimeout(networkRequestInfo: INetworkRequestInfo): Int {
        return if (networkRequestInfo.connectTimeout <= 0) DEFAULT_CONNECT_TIME_OUT else networkRequestInfo.connectTimeout
    }

    private fun getReadTimeout(networkRequestInfo: INetworkRequestInfo): Int {
        return if (networkRequestInfo.readTimeout <= 0) DEFAULT_CONNECT_TIME_OUT else networkRequestInfo.readTimeout

    }

    private fun getWriteTimeout(networkRequestInfo: INetworkRequestInfo): Int {
        return if (networkRequestInfo.writeTimeout <= 0) DEFAULT_CONNECT_TIME_OUT else networkRequestInfo.writeTimeout

    }


}