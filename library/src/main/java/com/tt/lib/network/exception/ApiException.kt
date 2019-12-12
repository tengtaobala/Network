package com.tt.lib.network.exception

import java.io.IOException

/**
 * Create by tengtao
 * on 2019-12-11 11:04
 */
class ApiException(val code: Int, message: String) : IOException(message)

