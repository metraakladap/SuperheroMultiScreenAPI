package com.example.superheroslistmultiscreen

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"

    fun getApi(): SuperheroApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SuperheroApi::class.java)
    }
}