package com.example.anto

import java.util.*

class Anto() {

    companion object{
        fun getInstance()= Anto()
    }

    fun getReference(key: String, thing: String, channel: String): AntoReference =  AntoReference(key, thing, channel)

    fun getReference(key: String, thing: String): AntoReference =  AntoReference(key, thing, "")
}