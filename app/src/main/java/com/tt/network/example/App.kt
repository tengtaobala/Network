package com.tt.network.example

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 *
 * Create by tengtao
 * on 2019-12-10 15:07
 */
class App : Application() {
    companion object{
        var CONTEXT:Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT=applicationContext
    }
}