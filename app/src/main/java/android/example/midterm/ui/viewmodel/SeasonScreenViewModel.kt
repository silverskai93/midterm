package android.example.midterm.ui.viewmodel
import android.example.midterm.data.model.model.ApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.SeasonsFetchRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class SeasonScreenViewModel(
    private val SeasonsApi: SeasonsFetchRepository
):ViewModel(){

    private var seasonList: ArrayList<Int>? = arrayListOf()
    val seasonArrayLiveData = MutableLiveData<ArrayList<Int>>(arrayListOf())
    var idData : String? = null

    fun seriesCall() {
        viewModelScope.launch {
            handleSeasonsCall(SeasonsApi.seriesCall("Game Of Thrones"))
        }
    }

    fun createSeasonsList(listcount: Int?) {
        if (listcount != null) {
            for (i in 1..listcount) {
                 seasonList?.add(i)
            }
            seasonArrayLiveData.postValue(seasonList)
        }
    }

    private fun handleSeasonsCall(result: ServiceResult<ApiResponse>){
        when(result){
            is ServiceResult.Success -> {
                createSeasonsList(result.data.totalSeasons)
                idData = result.data.imdbID
            }

            is ServiceResult.Error -> {
                Log.e("ViewModel", "Error when handling service call")
            }
        }
    }
}
