package com.example.dogsjetpack.service

import com.example.dogsjetpack.model.DogModel
import io.reactivex.Single
import retrofit2.http.GET

interface DogApi {
    // https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getFromApi(): Single<List<DogModel>>
}