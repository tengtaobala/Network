package com.tt.lib.network.exception

import java.lang.Exception

/**
 *
 * Create by tengtao
 * on 2019-12-11 11:31
 * http 异常
 */
class HttpException(val code: Int, message: String) : Exception(message) {
    companion object {
        //连接超时
        const val SOCKET_TIME_OUT = 10001
        //JSON解析异常
        const val MALFORMED_JSON = 10002
        //网络连接错误
        const val CONNECT_ERROR = 10003
        //未知错误
        const val UNKNOW = 10004
        //服务器错误
        const val HTTP_ERROE = 10006
        const val CODE_NO_NETWORK = 10007


        const val SOCK_TIME_OUT = "网络连接超时，请稍后后重试"
        const val JSON_SYNTAX = "数据解析错误，请联系客服"
        const val CONNECTION_EXCEPTION = "当前网络繁忙，请稍后后重试"
        const val HTTP_EXCEPTION = "获取数据异常，请联系客服"
        const val UNKNOW_EXCEPTION = "未知错误，请稍后重试或联系客服"
        const val NO_NETWORK = "无可用网络，请检查网络连接"
    }


}