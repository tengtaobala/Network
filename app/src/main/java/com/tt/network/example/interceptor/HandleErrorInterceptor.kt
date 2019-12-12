package com.tt.network.example.interceptor

import com.tt.lib.network.exception.ApiException
import com.tt.network.interceptor.ResponseBodyInterceptor
import okhttp3.HttpUrl
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException

class HandleErrorInterceptor : ResponseBodyInterceptor() {
    @Throws(IOException::class)
    override fun intercept(response: Response, url: HttpUrl?, readString: String?): Response {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(readString)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (jsonObject != null) {
            val state = jsonObject.optJSONObject("state")
            val code = state.optInt("code")
            val message = state.optString("msg")


            if (code != 0) {
                throw ApiException(code, message)
            }
            val res = JSONObject()
            res.put("code", code)
            res.put("message", message)
            res.put("data", jsonObject.optJSONObject("data").optJSONObject("localData"))
            val contentType = response.body?.contentType()
            val responseBody = ResponseBody.create(contentType, res.toString());
            return response.newBuilder().body(responseBody).build()
        }

        return response
    }


}

