package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class PersonDto(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("image") val image: ImageDto?,
        @SerializedName("birthday") val birthday: String?,
        @SerializedName("gender") val gender: String?,
        @SerializedName("country") val country: CountryDto?
        )