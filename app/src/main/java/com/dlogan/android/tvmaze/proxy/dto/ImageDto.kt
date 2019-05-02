package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
        @SerializedName("medium") val medium: String?,
        @SerializedName("original") val original: String?
)