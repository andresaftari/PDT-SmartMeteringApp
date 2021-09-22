package org.smartmetering.client.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.smartmetering.client.data.ApiStatus
import org.smartmetering.client.data.api.ApiConfig
import org.smartmetering.client.data.api.UnitResponseItem

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<ArrayList<UnitResponseItem>>()
    val data: LiveData<ArrayList<UnitResponseItem>>
        get() = _data

    private val _status = MutableLiveData<ApiStatus>()
    private val apiService = ApiConfig().service

    private suspend fun requestData() = try {
        _status.postValue(ApiStatus.LOADING)
        _data.postValue(apiService.getData())
        _status.postValue(ApiStatus.SUCCESS)
    } catch (ex: Exception) {
        Log.d("CheckDataList", ex.localizedMessage!!)
        _status.postValue(ApiStatus.FAILED)
    }

    fun getData() = viewModelScope.launch { requestData() }
}