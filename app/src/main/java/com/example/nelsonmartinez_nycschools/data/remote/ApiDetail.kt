package com.example.nelsonmartinez_nycschools.data.remote

import androidx.annotation.Keep
import com.example.nelsonmartinez_nycschools.data.models.Schools
import com.example.nelsonmartinez_nycschools.data.models.Scores
import retrofit2.Response
import retrofit2.http.GET

interface ApiDetail {

    @GET(ApiReference.SCHOOLS)
    suspend fun getSchools(): Response<Schools>

    @GET(ApiReference.SCORES)
    suspend fun getScores(): Response<Scores>
}