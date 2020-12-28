package com.example.dogs.api

import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {
    @GET("breeds/list/all")
    suspend fun getAllBreeds():ResultsResponse<Map<String, List<String>>>

    @GET("breed/{breed}/images")
    suspend fun getBreedImage(@Path("breed", encoded = true) breedName:String): ResultsResponse<List<String>>

    companion object{
        const val ENDPOINT = "https://dog.ceo/api/"
    }
}