package android.example.midterm.data.impl

import android.example.midterm.data.api.Api3
import android.example.midterm.data.model.model.OverViewData
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.OverviewFetchRepository
import android.example.midterm.data.retrofit.RetrofitCallHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class OverviewFetchRepositoryImpl (
    private val service: Api3,
    private val ioDispatcher: CoroutineDispatcher
): OverviewFetchRepository{
    override suspend fun overviewCall(
        stonksdata2: String?,
        seasonsID: String?
    ): ServiceResult<OverViewData> {
        return withContext(ioDispatcher) {
            RetrofitCallHandler.processCall {
                service.getOverview("ecf2c247","tt0944947", seasonsID, stonksdata2 )
            }
        }
    }
}