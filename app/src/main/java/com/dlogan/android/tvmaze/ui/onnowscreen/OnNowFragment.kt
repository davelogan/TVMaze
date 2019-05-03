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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.dlogan.android.tvmaze.R


class OnNowFragment : Fragment() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(ShowsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onnow, container, false)

//        view.findViewById<Button>(R.id.about_btn).setOnClickListener {
//            findNavController().navigate(R.id.action_title_to_about)
//        }

        // Create adapter for the RecyclerView
        val adapter = ShowAdapter()
        view.findViewById<RecyclerView>(R.id.show_list).adapter = adapter

        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        viewModel.currentShows.observe(this, Observer(adapter::submitList))

        return view
    }

    companion object {
        const val SCHEDULE_ID_KEY = "SCHEDULE_ID_KEY"
        const val SHOW_ID_KEY = "SHOW_ID_KEY"
    }

}
