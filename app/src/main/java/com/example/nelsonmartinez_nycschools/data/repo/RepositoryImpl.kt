package com.example.nelsonmartinez_nycschools.data.repo

import com.example.nelsonmartinez_nycschools.data.models.Schools
import com.example.nelsonmartinez_nycschools.data.models.ScoresItem
import com.example.nelsonmartinez_nycschools.data.remote.ApiDetail

import com.example.nelsonmartinez_nycschools.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiDetail: ApiDetail
) : Repository {
    override fun getSchools(): Flow<NetworkResult<Schools>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = apiDetail.getSchools()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResult.Success(it))
                }
            }
        }.catch { emit(NetworkResult.Error(it.message)) }
    }

    override fun getScores(dbn: String): Flow<NetworkResult<ScoresItem?>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = apiDetail.getScores()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResult.Success(
                        it.firstOrNull { it.dbn == dbn }
                    ))
                }
            }
        }.catch { emit(NetworkResult.Error(it.message)) }
    }
}