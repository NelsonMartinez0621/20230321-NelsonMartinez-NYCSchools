package com.example.nelsonmartinez_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nelsonmartinez_nycschools.data.models.Schools
import com.example.nelsonmartinez_nycschools.data.models.SchoolsItem
import com.example.nelsonmartinez_nycschools.data.models.ScoresItem
import com.example.nelsonmartinez_nycschools.data.repo.Repository
import com.example.nelsonmartinez_nycschools.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    var selectedSchool: SchoolsItem? = null

    private val _schoolList = MutableLiveData< NetworkResult<Schools>>()
    val schoolList: LiveData<NetworkResult<Schools>> = _schoolList

    private val _scoresList = MutableLiveData< NetworkResult<ScoresItem?>>()
    val scoresList: LiveData<NetworkResult<ScoresItem?>> = _scoresList

    init {
        getSchools()
    }

    private fun getSchools() = viewModelScope.launch {
            repository.getSchools().collect { result ->
                _schoolList.postValue(result)
            }
    }

    fun searchScores() {
        selectedSchool?.let {
            it.dbn?.let { dbn ->
                viewModelScope.launch {
                    repository.getScores(dbn).collect {
                        _scoresList.postValue(it)
                    }
                }
            } ?: _scoresList.postValue(NetworkResult.Error("dbn is null"))
        } ?: _scoresList.postValue(NetworkResult.Error("no schools selected"))

    }
}