package com.example.nelsonmartinez_nycschools.data.repo

import com.example.nelsonmartinez_nycschools.data.models.Schools
import com.example.nelsonmartinez_nycschools.data.models.Scores
import com.example.nelsonmartinez_nycschools.data.models.ScoresItem
import com.example.nelsonmartinez_nycschools.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    // using flows to move the data back to the viewmodel
    // in order to collect the flow value we need to call it from a coroutine scope
    // no need to have any suspend function
    // collect is already a suspend function
    fun getSchools(): Flow<NetworkResult<Schools>>
    fun getScores(dbn: String): Flow<NetworkResult<ScoresItem?>>
}