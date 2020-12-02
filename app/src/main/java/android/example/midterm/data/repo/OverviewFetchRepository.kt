package android.example.midterm.data.repo

import android.example.midterm.data.impl.OverviewFetchRepositoryImpl
import android.example.midterm.data.model.model.OverViewData
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.di.RetrofitService
import kotlinx.coroutines.Dispatchers

interface OverviewFetchRepository {

    suspend fun overviewCall(stonksdata2: String?, seasonsID: String?): ServiceResult<OverViewData>

    companion object{

        fun getOverviewFetch(
        ): OverviewFetchRepository{
            return OverviewFetchRepositoryImpl(
                service = RetrofitService().getOverviewApi(),
                ioDispatcher = Dispatchers.IO
            )
        }
    }
}