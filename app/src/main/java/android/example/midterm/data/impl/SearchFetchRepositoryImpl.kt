package android.example.midterm.data.impl

import android.example.midterm.data.api.Api4
import android.example.midterm.data.model.model.HomeApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.SearchFetchRepository
import android.example.midterm.data.retrofit.RetrofitCallHandler
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SearchFetchRepositoryImpl(
    private val service: Api4,
    private val ioDispatcher: CoroutineDispatcher
): SearchFetchRepository {
    override suspend fun searchCall(editvalue: String?): ServiceResult<HomeApiResponse>{
        return withContext(ioDispatcher){
            RetrofitCallHandler.processCall {
                service.getSerie(editvalue, "ecf2c247")
            }
        }
    }


}