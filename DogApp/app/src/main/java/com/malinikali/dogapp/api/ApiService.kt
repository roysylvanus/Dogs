package com.malinikali.dogapp.api

import com.malinikali.dogapp.model.BreedResponse
import com.malinikali.dogapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.LIST_OF_BREEDS)
    suspend fun getListOfBreeds():Response<BreedResponse>


    @GET(Constants.SEARCH_LIST)
    suspend fun searchList(@Query("q")query:String) :Response<BreedResponse>
}