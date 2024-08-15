package com.example.superheroslistmultiscreen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuperheroRepository @Inject constructor(
    private val api: SuperheroApi
) {
    suspend fun getSuperheros(): List<Superhero> {
        return withContext(Dispatchers.IO) {
            api.getSuperheros()
        }
    }
}