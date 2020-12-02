package android.example.midterm.data.repo

import android.example.midterm.data.impl.SearchFetchRepositoryImpl
import android.example.midterm.data.model.model.HomeApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.di.RetrofitService
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers

interface SearchFetchRepository {
    suspend fun searchCall(editvalue: String?): ServiceResult<HomeApiResponse>

    companion object{
        fun getSearchFetch(
        ): SearchFetchRepository{
            return SearchFetchRepositoryImpl(
                service = RetrofitService().getSeriesApi(),
                ioDispatcher = Dispatchers.IO
            )
        }
    }
}