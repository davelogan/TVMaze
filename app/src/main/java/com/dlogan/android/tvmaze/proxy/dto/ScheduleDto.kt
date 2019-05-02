package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class ScheduleDto(
        @SerializedName("time") val time: String,
        @SerializedName("days") val days: List<String>
)