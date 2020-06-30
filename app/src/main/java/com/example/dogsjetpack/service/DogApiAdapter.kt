package com.example.dogsjetpack.service

import com.example.dogsjetpack.model.DogModel
import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// Retrofit adapter

class DogApiAdapter {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DogApi::class.java)

    fun getData(): Single<List<DogModel>> {
        return retrofit.getFromApi()
    }
}