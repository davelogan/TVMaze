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

package com.dlogan.android.tvmaze.data.epg

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

/**
 * The Data Access Object for the ScheduledShow class.
 */
@Dao
interface ScheduledShowDao {
    @Query("SELECT * FROM epg ORDER BY showName")
    fun getShows(): LiveData<List<ScheduledShow>>

    @Query("SELECT * FROM epg WHERE id = :id")
    fun getShow(id: Long): ScheduledShow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(scheduledShows: List<ScheduledShow>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(scheduledShow: ScheduledShow)

    @Query("DELETE FROM epg")
    fun nukeTable()

    @Query("SELECT count(id) FROM epg")
    fun count(): Long

//    @Query("SELECT * FROM epg ORDER BY showName DESC")
//    fun allShows(): DataSource.Factory<Int, ScheduledShow>

    @Query("SELECT * FROM epg WHERE countryCode = :countryCode ORDER BY showName DESC")
    fun allShows(countryCode: String): DataSource.Factory<Int, ScheduledShow>

    @Query("SELECT * FROM epg WHERE countryCode = :countryCode AND (:nowDate BETWEEN startTime AND endTime) ORDER BY startTime DESC")
    fun onNowShows(nowDate: Date, countryCode: String): DataSource.Factory<Int, ScheduledShow>

    //@Query("SELECT * FROM epg WHERE :nowDate BETWEEN startTime AND endTime ORDER BY startTime DESC")
    //fun onNowShows(nowDate: Date): DataSource.Factory<Int, ScheduledShow>
}
