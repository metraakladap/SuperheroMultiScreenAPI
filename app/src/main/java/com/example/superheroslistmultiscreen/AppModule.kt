package com.example.superheroslistmultiscreen

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSuperheroApi(): SuperheroApi {
        return RetrofitClient.getApi()
    }

    @Provides
    @Singleton
    fun provideSuperheroRepository(api: SuperheroApi): SuperheroRepository {
        return SuperheroRepository(api)
    }
}