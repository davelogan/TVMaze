package com.dlogan.android.tvmaze.utilities

import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {


    companion object {
        //TODO use good date/time lib

        val locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)

        //example: 2019-04-30T04:00:00+00:00
        //var dateFormatter = SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'")
        var dateFormatter = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ", locale)


        var displayDateFormat = SimpleDateFormat("MM-dd-yyyy hh:mm a", locale)

    }

    fun parse(date: String) : Date {
        return dateFormatter.parse(date)
    }
}