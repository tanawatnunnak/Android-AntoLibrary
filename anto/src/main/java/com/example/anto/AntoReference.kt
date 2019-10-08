package com.example.anto

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AntoReference(private var key: String, private var thing: String, private var channel: String) {
    private var timer: Timer? = null
    private var responseAnto = ResponseAnto()
    private val antoService = CreateAntoService.create()

    fun addValueEventListener(valueEventListener: ValueEventListener) : AntoReference{
        val runnable = Runnable {
            antoService.getValue(key, thing, channel).enqueue(object : Callback<ResponseAnto> {
                override fun onFailure(call: Call<ResponseAnto>, t: Throwable) {
                    valueEventListener.onCancelled(t.message)
                }

                override fun onResponse(call: Call<ResponseAnto>, response: Response<ResponseAnto>) {
                    if (responseAnto != response.body()) {
                        responseAnto = response.body()!!
                        valueEventListener.onDataChange(response.body())
                    }
                }
            })
        }
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                Thread(runnable).start()
            }
        }, 0, 1000)
        return this
    }

    fun addSingleValueEventListener(valueEventListener: ValueEventListener) {
        antoService.getValue(key, thing, channel).enqueue(object : Callback<ResponseAnto> {
            override fun onFailure(call: Call<ResponseAnto>, t: Throwable) {
                valueEventListener.onCancelled(t.message)
            }

            override fun onResponse(call: Call<ResponseAnto>, response: Response<ResponseAnto>) {
                valueEventListener.onDataChange(response.body())
            }

        })
    }

    fun removeEventListener() {
        if (timer != null) timer?.cancel()
    }

    fun addChannel(channel: String): AntoReference{
        return AntoReference(key, thing, channel)
    }

    fun setValue(value: String, valueEventListener: ValueEventListener) {
        antoService.setValue(key, thing, channel, value).enqueue(object : Callback<ResponseAnto> {
            override fun onFailure(call: Call<ResponseAnto>, t: Throwable) {
                valueEventListener.onCancelled(t.message)
            }

            override fun onResponse(call: Call<ResponseAnto>, response: Response<ResponseAnto>) {
                valueEventListener.onDataChange(response.body())
            }

        })
    }

    fun setValue(value: String) {
        antoService.setValue(key, thing, channel, value).enqueue(object : Callback<ResponseAnto> {
            override fun onFailure(call: Call<ResponseAnto>, t: Throwable) {
                //valueEventListener.onCancelled(t.message)
            }

            override fun onResponse(call: Call<ResponseAnto>, response: Response<ResponseAnto>) {
                //valueEventListener.onDataChange(response.body())
            }
        })
    }
}