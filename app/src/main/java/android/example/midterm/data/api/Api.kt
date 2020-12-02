package android.example.midterm.data.api

import android.example.midterm.data.model.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/")
    suspend fun getSerie(
        @Query("apiKey") apiKey: String, @Query("t") showName: String?
    ): Response<ApiResponse>
}