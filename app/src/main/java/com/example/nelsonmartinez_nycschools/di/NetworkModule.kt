package com.example.nelsonmartinez_nycschools.di

import com.example.nelsonmartinez_nycschools.data.remote.ApiDetail
import com.example.nelsonmartinez_nycschools.data.remote.ApiReference
import com.example.nelsonmartinez_nycschools.data.repo.Repository
import com.example.nelsonmartinez_nycschools.data.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    // Provide Retrofit for gathering data from the API
    @Provides
    @Singleton
    fun provideRetrofit(): ApiDetail {
        return Retrofit.Builder()
            .baseUrl(ApiReference.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiDetail::class.java)
    }

    //Provide the repository Implementation
    @Provides
    @Singleton
    fun provideRepository(
        apiDetail: ApiDetail
    ): Repository {
        return RepositoryImpl(apiDetail)
    }
}