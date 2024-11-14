package com.example.lab8_monte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var startButton: Button
    private lateinit var toggleSwitch: Switch
    private var isSwitchOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        startButton = findViewById(R.id.button)
        toggleSwitch = findViewById(R.id.switch1)

        toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            isSwitchOn = isChecked
        }

        startButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                textView.text = longTask().await()
            }
        }
    }

    fun longTask() = CoroutineScope(Dispatchers.Default).async {
        var totalCount = 0
        var innerCount = 0

        for (bigLoop in 1..100) {
            for (smallLoop in 1..1_000_000) {
                val x = Math.random().toFloat()
                val y = Math.random().toFloat()

                if (x * x + y * y <= 1) innerCount++
                totalCount++
            }

            val currentValue = (innerCount.toDouble() / totalCount) * 4.0

            CoroutineScope(Dispatchers.Main).launch {
                textView.text = "Done ${bigLoop}%...\n" +
                        "current estimation: ${String.format("%.6f", currentValue)}\n"
            }
        }

        val lastValue = (innerCount.toDouble() / totalCount) * 4.0
        return@async "Done!\nEstimation: ${String.format("%.6f", lastValue)}"
    }
}