package com.example.anto_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.anto.Anto
import com.example.anto.AntoReference
import com.example.anto.ResponseAnto
import com.example.anto.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val KEY: String = "31m8mobIG9NPhKqNXgFWTKHL6iJOt7sjYgEa5xeh"
    private val THING: String = "smartHome"
    private val CHANNEL_BEDROOM: String = "light_bedroom"
    private val CHANNEL_TEMPERATURE: String = "temperature"
    private val CHANNEL_HUMIDITY: String = "humidity"

    private lateinit var antoReference: AntoReference
    private lateinit var temperature: AntoReference
    private lateinit var humidity: AntoReference
    private lateinit var bedroom: AntoReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialView()
    }

    private fun initialView() {
        antoReference = Anto.getInstance().getReference(KEY, THING)
        temperature = antoReference.addChannel(CHANNEL_TEMPERATURE).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(responseAnto: ResponseAnto?) {
                responseAnto?.let {
                    text_value_temperature.text = responseAnto.value
                }
            }

            override fun onCancelled(dataBaseError: String?) {

            }
        })

        humidity = antoReference.addChannel(CHANNEL_HUMIDITY).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(responseAnto: ResponseAnto?) {
                responseAnto?.let {
                    text_value_humidity.text = responseAnto.value
                }
            }

            override fun onCancelled(dataBaseError: String?) {

            }
        })

        bedroom = antoReference.addChannel(CHANNEL_BEDROOM).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(responseAnto: ResponseAnto?) {
                responseAnto?.let {
                    text_value_bedroom.text = when (responseAnto.value) {
                        "1" -> "เปิด".also { button_stop_read_anto.text = "OFF" }
                        else -> "ปิด".also { button_stop_read_anto.text = "ON" }
                    }
                }
            }

            override fun onCancelled(dataBaseError: String?) {
            }

        })

        button_stop_read_anto.setOnClickListener {
            when (button_stop_read_anto.text) {
                "ON" -> bedroom.setValue("1").also { button_stop_read_anto.text = "OFF" }
                else -> bedroom.setValue("0").also { button_stop_read_anto.text = "ON" }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        temperature.removeEventListener()
        humidity.removeEventListener()
        bedroom.removeEventListener()
    }
}
