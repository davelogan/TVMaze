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
import androidx.paging.Config
import androidx.paging.toLiveData
import com.dlogan.android.tvmaze.data.epg.EpgDatabase
import com.dlogan.android.tvmaze.utilities.COUNTRY_CODE
import java.util.*

/**
 * A simple ViewModel that provides a paged all_shows shows.
 */
class OnNowShowsViewModel(app: Application) : AndroidViewModel(app) {
    val dao = EpgDatabase.getDatabase(app).scheduledShowDao()

    /**
     * We use -ktx Kotlin extension functions here, otherwise you would use LivePagedListBuilder(),
     * and PagedList.Config.Builder()
     */
    val currentShows = dao.onNowShows(Date(), COUNTRY_CODE).toLiveData(Config(
            pageSize = 30,
            enablePlaceholders = true,
            maxSize = 200))

    fun refresh() {

        //itemDataSourceFactory.getItemLiveDataSource().getValue().invalidate()
    }
}
