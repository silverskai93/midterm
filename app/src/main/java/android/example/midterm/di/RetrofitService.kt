package android.example.midterm.di

import android.example.midterm.data.api.Api
import android.example.midterm.data.api.Api2
import android.example.midterm.data.api.Api3
import android.example.midterm.data.api.Api4
import android.example.midterm.data.retrofit.RetrofitFactory

class RetrofitService {
    companion object {
        const val BASE_URL = "https://www.omdbapi.com/"
    }

    fun getSeasonsApi(): Api = RetrofitFactory.retrofitProvider(Api::class.java, BASE_URL)
    fun getEpisodesApi(): Api2 = RetrofitFactory.retrofitProvider(Api2::class.java, BASE_URL)
    fun getOverviewApi(): Api3 = RetrofitFactory.retrofitProvider(Api3::class.java, BASE_URL)
    fun getSeriesApi(): Api4 = RetrofitFactory.retrofitProvider(Api4::class.java, BASE_URL)
}

