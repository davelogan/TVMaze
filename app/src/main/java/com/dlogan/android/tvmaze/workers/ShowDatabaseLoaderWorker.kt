/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dlogan.android.tvmaze.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dlogan.android.tvmaze.data.EpgDatabase
import com.dlogan.android.tvmaze.data.ScheduledShow
import com.dlogan.android.tvmaze.proxy.TVMazeApiService
import com.dlogan.android.tvmaze.proxy.dto.ScheduleItemDto
import com.dlogan.android.tvmaze.utilities.DaoMapper

class ShowDatabaseLoaderWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        try {
            val api = TVMazeApiService.create()
            val shows = api.getShows().execute()
            if (shows.isSuccessful) {
                shows.let {
                    sync(it.body()!!)
                    return Result.success()
                }
            }
            return Result.failure()
        } catch (ex: Exception) {
            return Result.failure()
        }
    }

    private fun sync(scheduleItems: List<ScheduleItemDto>) {
        val db = EpgDatabase.getDatabase(applicationContext)
        //delete everything, the data is only good for one day at most
        //db.scheduledShowDao().nukeTable()

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