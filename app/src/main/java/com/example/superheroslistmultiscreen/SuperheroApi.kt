package com.example.superheroslistmultiscreen

import com.example.superheroslistmultiscreen.Superhero
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface SuperheroApi {
    @GET("all.json")
    fun getSuperheros(): Single<List<Superhero>>
}