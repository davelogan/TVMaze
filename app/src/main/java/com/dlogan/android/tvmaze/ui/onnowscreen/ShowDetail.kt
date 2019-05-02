/*
 * Copyright 2019, The Android Open Source Project
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

package com.dlogan.android.tvmaze.ui.onnowscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.data.EpgDatabase
import com.dlogan.android.tvmaze.ui.onnowscreen.OnNowFragment.Companion.SCHEDULE_ID_KEY
import com.dlogan.android.tvmaze.ui.onnowscreen.OnNowFragment.Companion.SHOW_ID_KEY


/**
 * Shows a profile screen for a user, taking the name from the arguments.
 */
class ShowDetail : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.fragment_show_detail, container, false)

        val scheduleId = arguments?.getLong(SCHEDULE_ID_KEY)
        val showId = arguments?.getLong(SHOW_ID_KEY)

        val show = EpgDatabase.getDatabase(this.context!!).scheduledShowDao().getShow(scheduleId!!)

        //view.findViewById<TextView>(R.id.profile_user_name).text = name
        return view
    }
}
