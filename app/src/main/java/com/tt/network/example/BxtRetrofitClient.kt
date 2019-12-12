package com.tt.network.example

import com.tt.lib.network.BaseRetrofitClient
import com.tt.network.example.service.BoXingTongService
import okhttp3.OkHttpClient

/**
 *
 * Create by tengtao
 * on 2019-12-10 15:30
 */
object BxtRetrofitClient : BaseRetrofitClient() {
    val service by lazy { getService(BoXingTongService::class.java,NetworkRequestInfo()) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {

    }

}