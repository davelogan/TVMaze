package com.dlogan.android.tvmaze.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dlogan.android.tvmaze.data.EpgDatabase
import com.dlogan.android.tvmaze.data.ScheduledShow
import com.dlogan.android.tvmaze.proxy.TVMazeApiService
import com.dlogan.android.tvmaze.proxy.dto.ScheduleItemDto
import com.dlogan.android.tvmaze.utilities.DaoMapper
import com.dlogan.android.tvmaze.utilities.LogUtil
import com.dlogan.android.tvmaze.utilities.Prefs

class ShowDatabaseLoaderWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        //refresh at most every 12 hours
        const val minInterval = 43200000L

        //1 min
        //const val minInterval = 60000L
    }

    override fun doWork(): Result {
        val prefs = Prefs(applicationContext)

        val lastSyncTime = prefs.getLastSyncTimestamp()
        val nowTime = System.currentTimeMillis()

        if (nowTime-lastSyncTime < minInterval) {
            // No need to get the data from the api again
            LogUtil.debug("ShowDatabaseLoaderWorker", "No need to get the data from the api again. We already have updated data")
            return Result.success()
        }

        try {
            val api = TVMazeApiService.create()
            val shows = api.getShows().execute()
            if (shows.isSuccessful) {
                shows.let {
                    sync(it.body()!!)

                    //save last sync time
                    prefs.setLastSyncTimestamp(System.currentTimeMillis())

                    return Result.success()
                }
            }
            return Result.failure()
        } catch (ex: Exception) {
            return Result.failure()
        }
    }

    private fun sync(scheduleItems: List<ScheduleItemDto>) {
        LogUtil.debug("ShowDatabaseLoaderWorker", "syncing db")
        val db = EpgDatabase.getDatabase(applicationContext)

        for(item in scheduleItems) {
            var show: ScheduledShow? = convert(item)
            if (show != null) {
                db.scheduledShowDao().insert(show)
            }
        }
    }

    private fun convert(dto: ScheduleItemDto): ScheduledShow? {
        return try  {
            DaoMapper.convert(dto)
        } catch (ex: java.lang.Exception) {
            null
        }
    }
}