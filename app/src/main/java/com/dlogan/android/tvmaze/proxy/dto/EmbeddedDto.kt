package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class EmbeddedDto(
        @SerializedName("show") val show: ShowDto
)