package com.dlogan.android.tvmaze.utilities

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    fun getLastSyncTimestamp() = prefs.getLong(LAST_SYNC_TIME, 0L)

    fun setLastSyncTimestamp(lastSyncTime: Long) {
        prefs.edit().putLong(LAST_SYNC_TIME, lastSyncTime).apply()
    }
}