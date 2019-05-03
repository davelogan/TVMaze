package com.dlogan.android.tvmaze.proxy

import com.dlogan.android.tvmaze.proxy.dto.CastMemberDto
import com.dlogan.android.tvmaze.proxy.dto.PersonDto
import com.dlogan.android.tvmaze.proxy.dto.ScheduleItemDto
import com.dlogan.android.tvmaze.proxy.dto.ShowDto
import com.dlogan.android.tvmaze.utilities.MAZE_API_URL_BASE
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TVMazeApiService {

    companion object Factory {
        fun create(): TVMazeApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(MAZE_API_URL_BASE)
                    .build()

            return retrofit.create(TVMazeApiService::class.java)
        }
    }

    @GET("/schedule/full")
    fun getShows(): Call<List<ScheduleItemDto>>

    @GET("/shows/{showId}")
    fun getShow(@Path(value = "showId", encoded = true) showId: Long): Call<ShowDto>

    @GET("/shows/{showId}/cast")
    fun getCast(@Path(value = "showId", encoded = true) showId: Long): Call<List<CastMemberDto>>

    @GET("/people/{personId}")
    fun getPerson(@Path(value = "personId", encoded = true) personId: Long): Call<PersonDto>
}