package com.example.dogs.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(
    @SerializedName("message")
    val message: T,
    @SerializedName("status")
    val status: String
)