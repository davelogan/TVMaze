package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class NetworkDto(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("country") val country: CountryDto
)