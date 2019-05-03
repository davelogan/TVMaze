package com.dlogan.android.tvmaze.proxy

import android.util.Log
import com.dlogan.android.tvmaze.proxy.dto.CastMemberDto
import com.dlogan.android.tvmaze.proxy.dto.ShowDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVMazeApiServiceImpl {

    companion object {
        private val TAG = TVMazeApiServiceImpl::class.qualifiedName

        @Volatile
        private var INSTANCE: TVMazeApiServiceImpl? = null
        fun getInstance() =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: TVMazeApiServiceImpl().also {
                        INSTANCE = it
                    }
                }
    }

    //=======================================
    // getShow
    //=======================================

    fun getShow(req: GetShowRequest) {
        TVMazeApiService.create().getShow(req.getShowId()).enqueue(object : Callback<ShowDto> {
            override fun onResponse(call: Call<ShowDto>, response: Response<ShowDto>) {
                if (response.isSuccessful) {
                    req.getResponseCallback().onDataReceived(response.body()!!)
                } else {
                    Log.e(TAG, String.format("Get Shows request with: %s", response.message()))
                }
            }

            override fun onFailure(call: Call<ShowDto>, t: Throwable) {
                Log.e(TAG, String.format("Get Shows request failed with: %s", t.toString()))
                req.getResponseCallback().onDataFailure(t.localizedMessage)
            }
        })
    }

    interface GetShowRequest: TVMazeRequest<ShowDto> {
        override fun getResponseCallback(): ResponseCallback<ShowDto>
        fun getShowId(): Long
    }

    //=======================================
    // getCast
    //=======================================

    fun getCast(req: GetCastRequest) {
        TVMazeApiService.create().getCast(req.getShowId()).enqueue(object : Callback<List<CastMemberDto>> {
            override fun onResponse(call: Call<List<CastMemberDto>>, response: Response<List<CastMemberDto>>) {
                if (response.isSuccessful) {
                    req.getResponseCallback().onDataReceived(response.body()!!)
                } else {
                    Log.e(TAG, String.format("Get Cast request with: %s", response.message()))
                }
            }

            override fun onFailure(call: Call<List<CastMemberDto>>, t: Throwable) {
                Log.e(TAG, String.format("Get Shows Cast failed with: %s", t.toString()))
                req.getResponseCallback().onDataFailure(t.localizedMessage)
            }
        })
    }

    interface GetCastRequest: TVMazeRequest<List<CastMemberDto>> {
        override fun getResponseCallback(): ResponseCallback<List<CastMemberDto>>
        fun getShowId(): Long
    }

    //=======================================
    // Generic interfaces
    //=======================================

    interface ResponseCallback<T> {
        fun onDataReceived(data: T)

        fun onDataFailure(error: String?)
    }

    interface TVMazeRequest<T> {
        fun getResponseCallback(): ResponseCallback<T>
    }
}