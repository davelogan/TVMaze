package com.dlogan.android.tvmaze.ui

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_onnow.view.*

abstract class ShowsFragment: Fragment() {

    companion object {
        const val SCHEDULE_ID_KEY = "SCHEDULE_ID_KEY"
        const val SHOW_ID_KEY = "SHOW_ID_KEY"
    }

    fun stopRefreshDisplay() {
        if (view?.swipe_container?.isRefreshing!!) {

            //this.activity?.runOnUiThread { view?.swipe_container?.isRefreshing = false }

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                view?.swipe_container?.isRefreshing = false
            }, 1000)
        }
    }




}