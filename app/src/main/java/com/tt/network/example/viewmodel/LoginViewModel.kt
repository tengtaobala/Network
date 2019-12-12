package com.tt.network.example.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.tt.network.example.App
import com.tt.lib.network.core.Result
import com.tt.network.example.viewmodel.repository.LoginRepository
import kotlinx.coroutines.*

/**
 *
 * Create by tengtao
 * on 2019-12-10 16:08
 */
class LoginViewModel {

    private val presenterScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + Job())
    }

    fun login(context: Context, callback: (Any) -> Unit,loading:()->Unit) {
        presenterScope.launch {
            withContext(Dispatchers.Main) { loading() }
            val result = LoginRepository.getArticleList(context, 1)
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    Log.e("debug", result.data.toString())
                    callback(result.data.toString())
                } else {
                    val error = result as Result.Error
                    error.exception
                    Toast.makeText(App.CONTEXT, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }


    }


}