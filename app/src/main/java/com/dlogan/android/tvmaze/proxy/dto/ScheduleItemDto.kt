package com.dlogan.android.tvmaze.proxy.dto

import com.dlogan.android.tvmaze.utilities.DateTimeUtils
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

data class ScheduleItemDto(
        @SerializedName("id") val id: Long,
        @SerializedName("url") val url: String,
        @SerializedName("name") val name: String,
        @SerializedName("season") val season: Int?,
        @SerializedName("number") val number: Int?,
        @SerializedName("airdate") val airdate: String?,
        @SerializedName("airtime") val airtime: String?,
        @SerializedName("airstamp") val airstamp: String,
        @SerializedName("runtime") val runtime: Long = 0,
        @SerializedName("image") val image: ImageDto?,
        @SerializedName("summary") val summary: String?,
        @SerializedName("_links") val links: LinksDto?,
        @SerializedName("_embedded") val embedded: EmbeddedDto
) {
    fun getEndTime(startTime: Date): Date {
        val endMills = startTime.time + TimeUnit.MINUTES.toMillis(runtime)
        return Date(endMills)
    }

    fun getStartTime(): Date {
        return DateTimeUtils.dateFormatter.parse(airstamp)
    }
}