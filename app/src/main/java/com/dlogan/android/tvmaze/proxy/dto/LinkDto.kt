package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class LinkDto(
        @SerializedName("href") val href: String?
)
