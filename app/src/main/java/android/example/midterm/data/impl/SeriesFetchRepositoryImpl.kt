package android.example.midterm.data.impl

import android.example.midterm.data.api.Api4
import android.example.midterm.data.model.model.HomeApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.SeriesFetchRepository
import android.example.midterm.data.retrofit.RetrofitCallHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SeriesFetchRepositoryImpl(
    private val fourthService: Api4,
    private val ioDispatcher: CoroutineDispatcher
) : SeriesFetchRepository {
    override suspend fun serieCall(t: String
    ): ServiceResult<HomeApiResponse> {
        return withContext(ioDispatcher){
            RetrofitCallHandler.processCall {
                fourthService.getSerie(t, "ecf2c247")
            }
        }
    }
}