package com.example.dogs.breeds.ui.breedphotos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.breeds.data.BreedsRepository
import kotlinx.coroutines.launch

class BreedPhotosViewModel @ViewModelInject constructor(private val repository: BreedsRepository): ViewModel() {
    private val _breedImages: MutableLiveData<List<String>> = MutableLiveData()
    val breedImages: LiveData<List<String>> = _breedImages


    fun getBreedImages(breed: String){
        viewModelScope.launch {
            val images = repository.getBreedImage(breed)
            _breedImages.value = images.message
        }
    }
}