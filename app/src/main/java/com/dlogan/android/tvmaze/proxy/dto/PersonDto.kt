package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class PersonDto(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("image") val image: ImageDto?,
        @SerializedName("birthday") val birthday: String? = "Unknown",
        @SerializedName("deathday") val deathday: String? = "Unknown",
        @SerializedName("gender") val gender: String? = "Unknown",
        @SerializedName("country") val country: CountryDto?
        )