/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dlogan.android.tvmaze.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData

import com.dlogan.android.tvmaze.data.epg.EpgDatabase
import com.dlogan.android.tvmaze.data.epg.EpgRepository
import com.dlogan.android.tvmaze.data.epg.ScheduledShow
import com.dlogan.android.tvmaze.utilities.LogUtil
import java.util.*

/**
 * A simple ViewModel that provides a paged all_shows shows.
 */
class OnNowShowsViewModel(app: Application) : AndroidViewModel(app) {
    val dao = EpgDatabase.getDatabase(app).scheduledShowDao()

    val repo = EpgRepository.getInstance(dao)


    private val date = MutableLiveData<Date>().apply { value = Date() }

    val currentShows: LiveData<PagedList<ScheduledShow>> = Transformations.switchMap(date) {
            repo.getOnNowShows(it).toLiveData(Config(
                    pageSize = 30,
                    enablePlaceholders = true,
                    maxSize = 200))
    }

    fun refresh() {
        val now = Date()
        date.setValue(now)
        LogUtil.debug("OnNowShowsViewModel", "refresh()")
    }
}
