package com.tt.network.interceptor

import android.os.Build
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

abstract class ResponseBodyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val response = chain.proceed(request)
        response?.body?.let { responseBody ->
            val contentLength = responseBody.contentLength()
            val source = responseBody.source()
            source.request(Long.MAX_VALUE)
            var buffer = source.buffer()


            if ("gzip".equals(response.header("Content-Encoding"), ignoreCase = true)) {
                GzipSource(buffer.clone()).use { gzippedResponseBody ->
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                }
            }

            val contentType = responseBody.contentType()
            val charset: Charset =
                contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8

            if (contentLength != 0L) {
                return intercept(response, url, buffer.clone().readString(charset))
            }


        }

        return response
    }

    @Throws(IOException::class)
    abstract fun intercept(response: Response, url: HttpUrl?, readString: String?): Response

}