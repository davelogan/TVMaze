package com.dlogan.android.tvmaze.utilities

import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateTimeUtils {
    companion object {
        //example: 2019-04-30T04:00:00+00:00
        //var dateFormatter = SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'")
        var dateFormatter = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ")

    }

    fun parse(date: String) : Date {
        return dateFormatter.parse(date)
    }
}