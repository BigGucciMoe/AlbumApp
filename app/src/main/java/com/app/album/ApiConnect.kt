package com.app.album

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnect {
    val baseUrl = "https://jsonplaceholder.typicode.com"

    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }
}