package com.tt.lib.network.utils

import android.content.Context
import android.util.MalformedJsonException
import com.google.gson.JsonSyntaxException
import com.tt.lib.network.exception.HttpException
import com.tt.lib.network.response.BaseResponse
import java.io.EOFException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.tt.lib.network.core.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 *
 * Create by tengtao
 * on 2019-12-11 17:00
 */
object HttpUtil {

    suspend fun <T : Any> apiCall(call: suspend () -> BaseResponse<T>): BaseResponse<T> {
        return call.invoke()
    }


    suspend fun <T : Any> safeApiCall(
        context: Context,
        call: suspend () -> Result<T>
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            var ex = when (e) {
                is SocketTimeoutException ->
                    HttpException(HttpException.SOCKET_TIME_OUT, HttpException.SOCK_TIME_OUT)
                is MalformedJsonException, is JsonSyntaxException ->
                    HttpException(HttpException.MALFORMED_JSON, HttpException.JSON_SYNTAX)
                is ConnectException, is UnknownHostException, is SocketException, is EOFException ->
                    HttpException(HttpException.CONNECT_ERROR, HttpException.CONNECTION_EXCEPTION)
                is retrofit2.HttpException ->
                    HttpException(HttpException.HTTP_ERROE, HttpException.HTTP_EXCEPTION)
                else -> e
            }

            if (!NetworkUtils.isConnected(context.applicationContext)) {
                ex = HttpException(HttpException.CODE_NO_NETWORK, HttpException.NO_NETWORK)
            }
            Result.Error(ex)

        }
    }

    suspend fun <T : Any> executeResponse(
        response: BaseResponse<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {

        return coroutineScope {

            successBlock?.let { it() }
            Result.Success(response.data)

//            if (response.isSuccessfull()) {
//                successBlock?.let { it() }
//                Result.Success(response.data)
//            } else {
//                errorBlock?.let { it() }
//                Result.Error(ApiException(response.code, response.message))
//            }
        }
    }
}