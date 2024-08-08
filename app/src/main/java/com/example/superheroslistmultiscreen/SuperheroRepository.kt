package com.example.superheroslistmultiscreen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuperheroRepository {
    private val api = RetrofitClient.getApi()

    suspend fun getSuperheros(): List<Superhero> {
        return withContext(Dispatchers.IO) {
            api.getSuperheros()
        }
    }
}
