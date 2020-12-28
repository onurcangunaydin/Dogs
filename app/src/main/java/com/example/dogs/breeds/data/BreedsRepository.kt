package com.example.dogs.breeds.data

import com.example.dogs.api.DogsService
import com.example.dogs.api.ResultsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedsRepository @Inject constructor(private val dogsService: DogsService){

    suspend fun getAllBreeds(): List<DogBreed>{
        val response = dogsService.getAllBreeds()
        return convertDogBreedsResponse(response)
    }

    suspend fun getBreedImage(breedName: String): ResultsResponse<List<String>> = dogsService.getBreedImage(breedName)

    private suspend fun convertDogBreedsResponse(response: ResultsResponse<Map<String, List<String>>>): List<DogBreed>{
        return withContext(Dispatchers.IO){
            response.message.entries.map { DogBreed(it.key,it.value) }
        }
    }
}