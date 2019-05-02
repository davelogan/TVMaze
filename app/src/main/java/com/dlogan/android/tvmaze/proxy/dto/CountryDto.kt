package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
        @SerializedName("name") val name: String?,
        @SerializedName("code") val code: String,
        @SerializedName("timezone") val timezone: String?
)
