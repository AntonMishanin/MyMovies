package com.my.tv.remote

import com.my.tv.remote.dto.TvShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvRemoteDataSource(
    private val tvApi: TvApi
) {
    fun fetchPopularTvShows() {
        val request = tvApi.fetchPopularTvShows()
        request.enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                print(response.body())
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}