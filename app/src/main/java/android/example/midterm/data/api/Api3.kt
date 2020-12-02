package android.example.midterm.data.api

import android.example.midterm.data.model.model.OverViewData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api3 {
    @GET("/")
    suspend fun getOverview(
        @Query("apiKey")apiKey: String, @Query("i")showName:String?,
        @Query("Season")season:String?, @Query("Episode")episode: String?
    ): Response<OverViewData>
}