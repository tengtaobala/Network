package com.tt.network.example.viewmodel.repository

import android.content.Context
import com.tt.network.example.BxtRetrofitClient
import com.tt.lib.network.core.Result
import com.tt.lib.network.utils.HttpUtil
import com.tt.network.example.viewmodel.bean.ArticleList
import com.tt.network.example.viewmodel.bean.UserInfo

/**
 *
 * Create by tengtao
 * on 2019-12-10 17:28
 */
object LoginRepository {



    suspend fun getArticleList(context: Context,page: Int): Result<ArticleList> {
        return HttpUtil.safeApiCall(context,call = { requestArticleList(page) })
    }

    private suspend fun requestArticleList(page: Int): Result<ArticleList> =
        HttpUtil.executeResponse(BxtRetrofitClient.service.getHomeArticles(page))

}