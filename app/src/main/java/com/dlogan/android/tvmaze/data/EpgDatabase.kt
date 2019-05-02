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

package com.dlogan.android.tvmaze.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dlogan.android.tvmaze.utilities.DATABASE_NAME

/**
 * The Room database for this EPG (Electronic Programming API)
 */
@Database(entities = [ScheduledShow::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EpgDatabase : RoomDatabase() {
    abstract fun scheduledShowDao(): ScheduledShowDao

    companion object {
        @Volatile
        private var INSTANCE: EpgDatabase? = null

        fun getDatabase(context: Context): EpgDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        EpgDatabase::class.java,
                        DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
