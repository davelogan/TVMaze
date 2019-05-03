package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class CastMemberDto(
        @SerializedName("person") val person: PersonDto,
        @SerializedName("character") val character: CharacterDto,
        @SerializedName("self") val self: Boolean,
        @SerializedName("voice") val voice: Boolean
)