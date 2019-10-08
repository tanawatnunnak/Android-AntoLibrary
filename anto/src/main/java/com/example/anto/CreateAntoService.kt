package com.example.anto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CreateAntoService {
    private val URL: String = "https://api.anto.io/channel/"
    private var antoService: AntoService? = null
    fun create(): AntoService {
        if(antoService == null) {
             antoService = createRetrofit().create(AntoService::class.java)
        }
        return antoService!!
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}