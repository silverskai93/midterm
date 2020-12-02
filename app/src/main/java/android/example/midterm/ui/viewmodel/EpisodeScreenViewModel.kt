package android.example.midterm.ui.viewmodel

import android.example.midterm.data.model.model.EpisodeData
import android.example.midterm.data.model.model.EpisodeDetails
import android.example.midterm.data.model.model.SeasonsApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.EpisodesFetchRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EpisodeScreenViewModel(
    private val EpisodesApi: EpisodesFetchRepository
):ViewModel(){
    val episodeArrayLiveData = MutableLiveData<List<EpisodeDetails?>?>(arrayListOf())
    var seasonID : String? = null

    fun seasonCall(selectedItem: String?, imbdID: String?) {
        viewModelScope.launch {
            handleEpisodesCall(EpisodesApi.seasonCall(selectedItem, imbdID))
        }
    }
    private fun handleEpisodesCall(result: ServiceResult<EpisodeData>){
        when(result){
            is ServiceResult.Success -> {
                result.data
                episodeArrayLiveData.postValue(result.data.Episodes)
                seasonID = result.data.Season
            }
            is ServiceResult.Error -> {
                Log.e("ViewModel", "Error when handling service call")
            }
        }
    }
}