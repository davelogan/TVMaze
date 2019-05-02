package com.dlogan.android.tvmaze.proxy

import com.dlogan.android.tvmaze.proxy.dto.ScheduleItemDto
import com.dlogan.android.tvmaze.utilities.MAZE_API_URL_BASE
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TVMazeApiService {


    /**
     * Companion object to create the GithubApiService
     */
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

}