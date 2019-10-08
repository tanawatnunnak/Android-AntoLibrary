package com.example.anto

interface ValueEventListener {
    fun onDataChange(responseAnto: ResponseAnto)
    fun onCancelled(dataBaseError: String)
}