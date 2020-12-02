package android.example.midterm.ui.viewmodel

import android.example.midterm.data.model.model.HomeApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.SeriesFetchRepository
import android.example.midterm.util.SeriesSharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val SeriesApi: SeriesFetchRepository, private val seriesSharedPref: SeriesSharedPreferences
) : ViewModel() {
    val seriesArrayLiveData = MutableLiveData<ArrayList<String>?>(arrayListOf())
    private var seriesList: ArrayList<String> = arrayListOf("Game of Thrones", "Breaking Bad", "Chernobyl", "The Wire", "Rick and Morty", "Death Note", "Cowboy Bebop", "The Queen's Gambit")
    var finalList: ArrayList<String> = arrayListOf()

     fun SerieCall() {
        viewModelScope.launch {
            for (serie in seriesList) {
                serie.toString()
                handleSeriesCall(SeriesApi.serieCall(serie))
            }
            seriesArrayLiveData.postValue(finalList)
        }
//        seriesArrayLiveData.postValue(finalList)
        //seriesSharedPref.writeSeries(finalList.toString())
    }



    private fun handleSeriesCall(result: ServiceResult<HomeApiResponse>) {
        when (result) {
            is ServiceResult.Success -> {
                finalList.add(result.toString())
            }
            is ServiceResult.Error -> {
                Log.e("ViewModel", "Error when handling service call")
            }
        }
    }
}