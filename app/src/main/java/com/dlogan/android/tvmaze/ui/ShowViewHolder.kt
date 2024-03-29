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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.data.epg.ScheduledShow
import com.dlogan.android.tvmaze.ui.ShowsFragment.Companion.SCHEDULE_ID_KEY
import com.dlogan.android.tvmaze.ui.ShowsFragment.Companion.SHOW_ID_KEY
import com.dlogan.android.tvmaze.utilities.DateTimeUtils.Companion.displayDateFormat
import kotlinx.android.synthetic.main.show_list_item.view.*


class ShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private var show : ScheduledShow? = null

    companion object {
        fun create(parent: ViewGroup): ShowViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.show_list_item, parent, false)
            return ShowViewHolder(view)
        }
    }

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(show : ScheduledShow?) {
        this.show = show
        itemView.show_name.text = show?.showName
        itemView.episode_name.text = show?.episodeName

        if (show?.imageMedium != null) {
            Glide.with(itemView).load(show.imageMedium).into(itemView.show_image)
        } else {
            Glide.with(itemView).load(R.drawable.baseline_theaters_black_24).into(itemView.show_image)
        }

        try {
            itemView.show_time.text = String.format("Start Time: %s", displayDateFormat.format(show?.startTime))
        }catch (ex: Exception) {
            itemView.show_time.text = ""
        }

        itemView.setOnClickListener {
                val bundle = bundleOf(SCHEDULE_ID_KEY to this.show?.id, SHOW_ID_KEY to this.show?.showId)
            itemView.findNavController().navigate(
                        R.id.action_showlist_to_show_detail,
                        bundle)
            }

    }
}