package com.tt.lib.network.response


data class BaseResponse<out T>(val code: Int, val message: String, val data: T)  {
}
