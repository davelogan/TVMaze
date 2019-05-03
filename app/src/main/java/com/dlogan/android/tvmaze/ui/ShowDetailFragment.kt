/*
 * Copyright 2019, The Android Open Source Project
 *
 * L icensed under the Apache License, Version 2.0 (the "License");
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
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.proxy.TVMazeApiServiceImpl
import com.dlogan.android.tvmaze.proxy.dto.ShowDto
import com.dlogan.android.tvmaze.ui.ShowsFragment.Companion.SHOW_ID_KEY
import kotlinx.android.synthetic.main.fragment_show_detail.*
import kotlinx.android.synthetic.main.fragment_show_detail.view.*


/**
 * Shows a profile screen for a user, taking the name from the arguments.
 */
class ShowDetailFragment : Fragment(), TVMazeApiServiceImpl.ResponseCallback<ShowDto> {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.fragment_show_detail, container, false)
        val showId = arguments?.getLong(SHOW_ID_KEY)

        TVMazeApiServiceImpl.getInstance().getShow(GetShowRequest(showId!!, this))

        retainInstance = true


        view.cast_and_crew_btn.setOnClickListener {
            val bundle = bundleOf(SHOW_ID_KEY to showId)

            Navigation.findNavController(view).navigate(
                    R.id.action_user_detail_to_cast_list,
                    bundle)
        }

        return view
    }


    class GetShowRequest(val id: Long, private val callback: TVMazeApiServiceImpl.ResponseCallback<ShowDto>) : TVMazeApiServiceImpl.GetShowRequest {

        override fun getResponseCallback(): TVMazeApiServiceImpl.ResponseCallback<ShowDto> {
            return callback
        }

        override fun getShowId(): Long {
            return id
        }
    }

    override fun onDataReceived(data: ShowDto) {

        detail_show_name.text = data.name

        detail_summary.text = Html.fromHtml(data.summary) //TODO make this spannable
        //detail_season_episode.text = String.format("Season: %d Episode: %d", data.)
        detail_network.text = data.network?.name
        detail_schedule.text = data.schedule?.days?.toString()
        detail_status.text = data.status
        detail_type.text = data.type

        //set image
        if (data?.image?.original != null) {
            Glide.with(this.context!!).load(data?.image.original).into(detail_show_image)
        } else {
            Glide.with(this.context!!).load(R.drawable.baseline_theaters_black_36).into(detail_show_image)
        }


    }

    override fun onDataFailure(error: String?) {
        //TODO
    }
}
