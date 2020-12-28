package com.example.dogs.breeds.ui.breedlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dogs.BaseViewModel
import com.example.dogs.breeds.data.BreedsRepository
import com.example.dogs.breeds.data.DogBreed
import kotlinx.coroutines.launch

class BreedListViewModel @ViewModelInject constructor(private val repository: BreedsRepository): BaseViewModel(){

    private val _breedListLD = MutableLiveData<List<DogBreed>>()
    val allBreeds: LiveData<List<DogBreed>> = _breedListLD

    init {
        viewModelScope.launch {
            val breeds = repository.getAllBreeds()
            _breedListLD.value = breeds
        }
    }
}