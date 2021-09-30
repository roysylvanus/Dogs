package com.malinikali.dogapp.repository

import com.malinikali.dogapp.api.ApiService
import javax.inject.Inject


class DogRepository
@Inject constructor(private val apiService: ApiService){
    suspend fun getBreeds() = apiService.getListOfBreeds()

    suspend fun searchListOfBreeds(query:String) = apiService.searchList(query)
}