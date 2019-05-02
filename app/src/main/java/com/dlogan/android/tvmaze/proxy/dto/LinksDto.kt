package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class LinksDto(
        @SerializedName("self") val self: LinkDto?,
        @SerializedName("previousepisode") val previousepisode: LinkDto?,
        @SerializedName("nextepisode") val nextepisode: LinkDto?
)
