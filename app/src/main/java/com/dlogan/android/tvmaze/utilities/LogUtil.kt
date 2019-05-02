package com.dlogan.android.tvmaze.utilities

import android.util.Log
import com.dlogan.android.tvmaze.BuildConfig


object LogUtil {

    fun debug(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }
}