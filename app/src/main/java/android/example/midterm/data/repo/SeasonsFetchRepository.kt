package android.example.midterm.data.repo

import android.example.midterm.data.impl.SeasonsFetchRepositoryImpl
import android.example.midterm.data.model.model.ApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.di.RetrofitService
import kotlinx.coroutines.Dispatchers

interface SeasonsFetchRepository {

    suspend fun seriesCall(t: String): ServiceResult<ApiResponse>

    companion object{

        fun getSeasonsFetch(
        ): SeasonsFetchRepository{
            return SeasonsFetchRepositoryImpl(
                service = RetrofitService().getSeasonsApi(),
                ioDispatcher = Dispatchers.IO
            )
        }
    }
}