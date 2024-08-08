package com.example.superheroslistmultiscreen

import android.app.Application

class MyApplication : Application(){
    lateinit var repo: SuperheroRepository
    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = SuperheroRepository()
    }

    companion object {
        lateinit var instance: MyApplication
        fun getApp() = instance
    }
}