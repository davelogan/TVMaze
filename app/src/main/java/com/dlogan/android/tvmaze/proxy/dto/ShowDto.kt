package com.dlogan.android.tvmaze.proxy.dto

import com.dlogan.android.tvmaze.utilities.COUNTRY_CODE
import com.google.gson.annotations.SerializedName

data class ShowDto(
        @SerializedName("id") val id: Long,
        @SerializedName("url") val url: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("type") val type: String?,
        @SerializedName("language") val language: String?,
        @SerializedName("genres") val genres: List<String>?,
        @SerializedName("status") val status: String?,
        @SerializedName("runtime") val runtime: Int?,
        @SerializedName("premiered") val premiered: String?,
        @SerializedName("officialSite") val officialSite: String?,
        @SerializedName("schedule") val schedule: ScheduleDto?,
        @SerializedName("rating") val rating: RatingDto?,
        @SerializedName("weight") val weight: Int?,
        @SerializedName("network") val network: NetworkDto?,
        @SerializedName("webChannel") val webChannel: WebChannelDto?,
        @SerializedName("externals") val externals: ExternalsDto?,
        @SerializedName("image") val image: ImageDto?,
        @SerializedName("summary") val summary: String?,
        @SerializedName("updated") val updated: Long?,
        @SerializedName("_links") val links: LinksDto?
)
{
    //TODO refactor this to use Kotlin correctly
    fun getCountryCode(): String? {
        val networkCountryCode = network?.country?.code
        if (networkCountryCode != null && !networkCountryCode.isBlank()) {
            return networkCountryCode
        }

//        val webChannelCountryCode = webChannel?.country?.code
//        if (webChannelCountryCode != null) {
//            return webChannelCountryCode
//        }

        //TODO not 'really' safe to assume, but I need to know more about the data. A lot of web channels have no country code (like youtube and amazon) so I will assume they are available in the US
        return COUNTRY_CODE
    }
}