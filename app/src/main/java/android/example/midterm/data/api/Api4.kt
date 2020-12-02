package android.example.midterm.data.api

import android.example.midterm.data.model.model.HomeApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api4{
    @GET("/")
    suspend fun getSerie(
        @Query("t")showName: String?,
        @Query("apiKey")apiKey: String

    ): Response<HomeApiResponse>
}