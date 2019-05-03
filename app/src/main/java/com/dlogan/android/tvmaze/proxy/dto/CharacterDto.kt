package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("image") val image: ImageDto?
)