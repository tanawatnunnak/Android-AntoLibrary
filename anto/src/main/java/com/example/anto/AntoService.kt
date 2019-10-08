package com.example.anto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AntoService {

    @GET("get/{key}/{thing}/{channel}")
    fun getValue(@Path("key") key: String,
            @Path("thing") thing: String,
            @Path("channel") channel: String): Call<ResponseAnto>

    @GET("set/{key}/{thing}/{channel}/{value}")
    fun setValue(@Path("key") key: String,
            @Path("thing") thing: String,
            @Path("channel") channel: String,
            @Path("value") value: String): Call<ResponseAnto>
}