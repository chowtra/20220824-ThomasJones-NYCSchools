package com.rigo.nycschools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rigo.nycschools.api.DataLayer
import com.rigo.nycschools.models.School
import com.rigo.nycschools.models.Score
import kotlinx.coroutines.launch

/**
 * ViewModel for the screen of NYC screen list.
 */

class MainViewModel: ViewModel() {

    private val _schoolsLiveData = MutableLiveData<Boolean>()
    val schoolsLiveData: LiveData<Boolean> = _schoolsLiveData
    private val _scoreLiveData = MutableLiveData<Boolean>()
    val scoreLiveData: LiveData<Boolean> = _scoreLiveData

    /**
     * Call api endpoint for NYC schools data
     * Change the observable value - schoolsLiveData
     */

    fun refreshSchools() {
        viewModelScope.launch {
            val isSuccessful = DataLayer.fetchSchools()
            _schoolsLiveData.postValue(isSuccessful)
        }
    }

    /**
     * Call api endpoint for SAT score data
     * Change the observable value - scoresLiveData
     */

    fun refreshScores() {
        viewModelScope.launch {
            val isSuccessful = DataLayer.fetchScores()
            _scoreLiveData.postValue(isSuccessful)
        }
    }
}