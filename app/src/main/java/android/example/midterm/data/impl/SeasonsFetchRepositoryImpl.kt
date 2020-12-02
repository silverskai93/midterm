package android.example.midterm.data.impl

import android.example.midterm.data.api.Api
import android.example.midterm.data.model.model.ApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.SeasonsFetchRepository
import android.example.midterm.data.retrofit.RetrofitCallHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SeasonsFetchRepositoryImpl (
    private val service: Api,
    private val ioDispatcher: CoroutineDispatcher
): SeasonsFetchRepository{
    override suspend fun seriesCall(t: String): ServiceResult<ApiResponse> {
        return withContext(ioDispatcher) {
            RetrofitCallHandler.processCall {
                service.getSerie( "ecf2c247", t)
            }
        }
    }
}