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
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.proxy.TVMazeApiServiceImpl
import com.dlogan.android.tvmaze.proxy.dto.CastMemberDto
import com.dlogan.android.tvmaze.proxy.dto.PersonDto
import com.dlogan.android.tvmaze.proxy.dto.ShowDto
import kotlinx.android.synthetic.main.fragment_user_profile.*
import kotlinx.android.synthetic.main.list_view_item.view.*
import kotlinx.android.synthetic.main.user_card.*
import java.io.Serializable


/**
 * Shows a profile screen for a user, taking the name from the arguments.
 */
class UserProfileFragment : Fragment(), TVMazeApiServiceImpl.ResponseCallback<PersonDto> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        val personId = arguments?.getLong(CastListFragment.CAST_MEMBER_ID_KEY)


        TVMazeApiServiceImpl.getInstance().getPerson(GetPersonRequest(personId!!, this))

        retainInstance = true

        return view
    }

    class GetPersonRequest(val id: Long, private val callback: TVMazeApiServiceImpl.ResponseCallback<PersonDto>) : TVMazeApiServiceImpl.GetPersonRequest {

        override fun getResponseCallback(): TVMazeApiServiceImpl.ResponseCallback<PersonDto> {
            return callback
        }

        override fun getPersonId(): Long {
            return id
        }
    }

    override fun onDataReceived(data: PersonDto) {

        profile_user_name.text = data.name


//        detail_show_name.text = data.name
//
//        detail_summary.text = Html.fromHtml(data.summary) //TODO make this spannable
//        //detail_season_episode.text = String.format("Season: %d Episode: %d", data.)
//        detail_network.text = data.network?.name
//        detail_schedule.text = data.schedule?.days?.toString()
//        detail_status.text = data.status
//        detail_type.text = data.type
//
        //set image
        if (data?.image?.original != null) {
            Glide.with(this.context!!).load(data?.image.original).into(profile_pic)
        } else {
            Glide.with(this.context!!).load(R.drawable.baseline_theaters_black_36).into(profile_pic)
        }

        snippet1.text = String.format("Birthday: %s", data.birthday)
        snippet2.text = String.format("Deathday: %s", data.deathday)
        snippet3.text = String.format("Gender: %s", data.gender)
        snippet4.text = data.country?.name
    }

    override fun onDataFailure(error: String?) {
        //TODO
    }
}
