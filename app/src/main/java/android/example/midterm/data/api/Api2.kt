package android.example.midterm.data.api

import android.example.midterm.data.model.model.EpisodeData
import android.example.midterm.data.model.model.EpisodeDetails
import android.example.midterm.data.model.model.SeasonsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api2 {
    @GET("/")
    suspend fun getSandE(
        @Query("apikey") apiKey: String,
        @Query("i") series: String?,
        @Query("season") season: String?


    ): Response<EpisodeData>
}