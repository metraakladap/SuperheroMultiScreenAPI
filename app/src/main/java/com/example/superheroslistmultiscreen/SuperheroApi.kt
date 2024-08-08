package com.example.superheroslistmultiscreen


import retrofit2.http.GET

interface SuperheroApi {
    @GET("all.json")
    suspend fun getSuperheros(): List<Superhero>
}