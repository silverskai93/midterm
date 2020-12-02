package android.example.midterm.ui.viewmodel

import android.example.midterm.data.model.model.OverViewData
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.OverviewFetchRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OverviewScreenViewModel (
    private val OverviewApi: OverviewFetchRepository
): ViewModel(){
    val overviewArrayLiveData = MutableLiveData<OverViewData>()

    fun overviewCall(stonksdata2: String?, seasonsID: String?) {
        viewModelScope.launch {
        handleOverviewCall(OverviewApi.overviewCall(stonksdata2, seasonsID))
        }
    }

    private fun handleOverviewCall(result: ServiceResult<OverViewData>){
        when(result){
            is ServiceResult.Success -> overviewArrayLiveData.postValue(result.data)

            is ServiceResult.Error -> {
                Log.e("ViewModel", "Error when handling service call")
            }

        }
    }
}