package android.example.midterm.data.impl

import android.example.midterm.data.api.Api2
import android.example.midterm.data.model.model.EpisodeData
import android.example.midterm.data.model.model.SeasonsApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.EpisodesFetchRepository
import android.example.midterm.data.retrofit.RetrofitCallHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class EpisodesFetchRepositoryImpl(
    private val secondService: Api2,
    private val ioDispatcher: CoroutineDispatcher
) : EpisodesFetchRepository {
    override suspend fun seasonCall(
        selectedItem: String?, imbdID: String?
    ): ServiceResult<EpisodeData> {
        return withContext(ioDispatcher) {
            RetrofitCallHandler.processCall {
                secondService.getSandE("ecf2c247", imbdID, selectedItem)
            }
        }
    }
}