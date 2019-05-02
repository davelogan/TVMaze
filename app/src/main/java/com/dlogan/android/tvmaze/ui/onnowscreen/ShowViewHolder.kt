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

package com.dlogan.android.tvmaze.ui.onnowscreen

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.data.ScheduledShow


class ShowViewHolder(parent :ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.show_item_new, parent, false)) {

    private val showNameView = itemView.findViewById<TextView>(R.id.show_name)
    private val showImageView = itemView.findViewById<ImageView>(R.id.show_image)
    private val episodeNameView = itemView.findViewById<TextView>(R.id.episode_name)

    var show : ScheduledShow? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(show : ScheduledShow?) {
        this.show = show
        showNameView.text = show?.showName
        episodeNameView.text = show?.episodeName

        if (show?.imageMedium != null) {
            Glide.with(showImageView).load(show.imageMedium).into(showImageView)
        } else {
            Glide.with(showImageView).load(R.drawable.baseline_theaters_black_24).into(showImageView)
        }

    }
}