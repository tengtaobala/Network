package com.tt.network.example.service

import com.tt.lib.network.response.BaseResponse
import com.tt.network.example.viewmodel.bean.ArticleList
import com.tt.network.example.viewmodel.bean.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * Create by tengtao
 * on 2019-12-10 15:14
 */
interface BoXingTongService {
    companion object{
        const val BASE_URL = "https://www.wanandroid.com"

    }


    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BaseResponse<ArticleList>


}