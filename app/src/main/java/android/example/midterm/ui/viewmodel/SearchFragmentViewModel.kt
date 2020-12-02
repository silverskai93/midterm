package android.example.midterm.ui.viewmodel

import android.example.midterm.data.model.model.HomeApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.SearchFetchRepository
import android.example.midterm.util.SeriesSharedPreferences
import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val SearchApi: SearchFetchRepository,
    private val seriesSharedPref: SeriesSharedPreferences
) : ViewModel() {
    val searchResultsLiveData = MutableLiveData<String>()
    val whatever = "its kinda... wet in here"

    private val _displayedEditTextContent = MutableLiveData<String?>()
    val displayedEditTextContent: LiveData<String?> = _displayedEditTextContent


    fun onEditTextChanged(editable: Editable) {
    _displayedEditTextContent.value = editable.toString()

    }

    fun searchCall() {
        viewModelScope.launch {
            handleSearchCall(SearchApi.searchCall(displayedEditTextContent.value))

        }
    }

    private fun handleSearchCall(result: ServiceResult<HomeApiResponse>) {
        when (result) {
            is ServiceResult.Success -> {
                searchResultsLiveData.postValue(result.data.Title)
            }
            is ServiceResult.Error -> {
                Log.e("viewmodel", "Error when handling service call")
            }
        }
    }

}