package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class RatingDto(
        @SerializedName("average") val average: Double?

)