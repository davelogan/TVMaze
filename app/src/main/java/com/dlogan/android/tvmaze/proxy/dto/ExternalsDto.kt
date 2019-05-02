package com.dlogan.android.tvmaze.proxy.dto

import com.google.gson.annotations.SerializedName

data class ExternalsDto(
        @SerializedName("tvrage") val tvrage: Int?,
        @SerializedName("thetvdb") val thetvdb: Int?,
        @SerializedName("imdb") val imdb: String?

)