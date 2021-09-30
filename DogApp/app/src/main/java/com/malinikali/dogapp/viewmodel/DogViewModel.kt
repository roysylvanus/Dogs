package com.malinikali.dogapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malinikali.dogapp.model.BreedResponseItem
import com.malinikali.dogapp.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel
@Inject constructor(private val dogRepository: DogRepository):ViewModel(){

    private val _breeds = MutableLiveData<List<BreedResponseItem>>()
    val breeds:LiveData<List<BreedResponseItem>> get() = _breeds

    init {
        getListOfBreeds()
    }


    private fun getListOfBreeds() = viewModelScope.launch {
        dogRepository.getBreeds().let {
            response ->
            if (response.isSuccessful){
                _breeds.postValue(response.body())
            }
            else{
                Log.d("VIEWMODEL","${response.body()}")
            }
        }
    }

    fun addSearchName(query:String){
        searchList(query)

    }

    private fun searchList(query: String) = viewModelScope.launch {
        dogRepository.searchListOfBreeds(query).let {
            response ->
            if (response.isSuccessful){
                _breeds.postValue(response.body())
            }
            else{
                Log.d("VIEWMODEL","${response.body()}")
            }
        }
    }
}