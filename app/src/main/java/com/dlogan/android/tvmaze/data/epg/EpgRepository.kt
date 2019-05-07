package com.dlogan.android.tvmaze.data.epg

import androidx.paging.DataSource
import com.dlogan.android.tvmaze.utilities.COUNTRY_CODE
import java.util.*


class EpgRepository private constructor(private val scheduledShowDao: ScheduledShowDao) {

    fun getOnNowShows(nowDate: Date): DataSource.Factory<Int, ScheduledShow> {
        return scheduledShowDao.onNowShows(nowDate, COUNTRY_CODE)
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: EpgRepository? = null

        fun getInstance(plantDao: ScheduledShowDao) =
                instance ?: synchronized(this) {
                    instance ?: EpgRepository(plantDao).also { instance = it }
                }
    }
}
