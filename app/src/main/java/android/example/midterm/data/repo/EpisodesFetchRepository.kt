package android.example.midterm.data.repo

import android.example.midterm.data.impl.EpisodesFetchRepositoryImpl
import android.example.midterm.data.model.model.EpisodeData
import android.example.midterm.data.model.model.SeasonsApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.di.RetrofitService
import kotlinx.coroutines.Dispatchers

interface EpisodesFetchRepository {
    suspend fun seasonCall(selectedItem: String?, imbdID: String?): ServiceResult<EpisodeData>

    companion object{
        fun getEpisodesFetch(
        ): EpisodesFetchRepository{
            return EpisodesFetchRepositoryImpl(
                secondService = RetrofitService().getEpisodesApi(),
                ioDispatcher = Dispatchers.IO
            )
        }
    }
}