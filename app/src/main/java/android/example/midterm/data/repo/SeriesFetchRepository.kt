package android.example.midterm.data.repo


import android.example.midterm.data.impl.SeriesFetchRepositoryImpl
import android.example.midterm.data.model.model.HomeApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.di.RetrofitService
import kotlinx.coroutines.Dispatchers

interface SeriesFetchRepository {

    suspend fun serieCall(t: String): ServiceResult<HomeApiResponse>

    companion object{

        fun getSeriesFetch(
        ): SeriesFetchRepository{
            return SeriesFetchRepositoryImpl(
                fourthService = RetrofitService().getSeriesApi(),
                ioDispatcher = Dispatchers.IO
            )
        }
    }
}