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

package com.dlogan.android.tvmaze.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.data.epg.ScheduledShow
import com.dlogan.android.tvmaze.ui.viewmodels.AllShowsViewModel
import kotlinx.android.synthetic.main.fragment_onnow.view.*

/**
 * Shows a static leaderboard with multiple users.
 */
class AllShowsFragment : ShowsFragment() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(AllShowsViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_shows, container, false)

        // Create adapter for the RecyclerView
        val adapter = ShowAdapter()
        view.findViewById<RecyclerView>(R.id.show_list).adapter = adapter

        subscribeUi(adapter)

        view.swipe_container.setOnRefreshListener {
            viewModel.refresh()
        }

        return view
    }

    private fun subscribeUi(adapter: ShowAdapter) {
        viewModel.allShows.observe(viewLifecycleOwner, Observer { shows ->
            stopRefreshDisplay()
            if (shows != null) {
                adapter.submitList(shows)
            }
            displayNoShowsAvailableIfNeeded(shows)
        })
    }

    private fun displayNoShowsAvailableIfNeeded(shows: PagedList<ScheduledShow>?) {
        if (shows == null || shows.isEmpty()) {
            Toast.makeText(this.context, getString(R.string.text_no_onnow_shows_msg), Toast.LENGTH_SHORT).show()
        }
    }
}
