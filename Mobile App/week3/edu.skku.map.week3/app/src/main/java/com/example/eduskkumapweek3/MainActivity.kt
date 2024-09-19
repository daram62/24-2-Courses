package com.example.eduskkumapweek3

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var counter = 0
        val textView = findViewById<TextView>(R.id.textView)
        val imageview = findViewById<ImageView>(R.id.imageView)
        val left_btn = findViewById<Button>(R.id.button)
        val right_btn = findViewById<Button>(R.id.button2)

        textView.text = "pizza"
        imageview.setImageResource(R.drawable.pizza)

        left_btn.setOnClickListener{
            counter -= 1
            if (counter == 0) {
            textView.text = "pizza"
            imageview.setImageResource(R.drawable.pizza)
            }
            else if (counter == 1) {
                textView.text = "chicken"
                imageview.setImageResource(R.drawable.chicken)
            }
            else if (counter == 2) {
                textView.text = "hamburger"
                imageview.setImageResource(R.drawable.hamburger)
            }
            else if (counter == 3) {
                textView.text = "ramen"
                imageview.setImageResource(R.drawable.ramen)
            }
            else if (counter == -1) {
                Toast.makeText(this@MainActivity, "First Image", android.widget.Toast.LENGTH_SHORT).show()
                counter = 0
            }
        }

        right_btn.setOnClickListener{
            counter += 1
            if (counter == 1) {
                textView.text = "chicken"
                imageview.setImageResource(R.drawable.chicken)
            }
            else if (counter == 2) {
                textView.text = "hamburger"
                imageview.setImageResource(R.drawable.hamburger)
            }
            else if (counter == 3) {
                textView.text = "ramen"
                imageview.setImageResource(R.drawable.ramen)
            }
            else if (counter == 4) {
                Toast.makeText(this@MainActivity, "Last Image", android.widget.Toast.LENGTH_SHORT).show()
                counter = 3
            }
        }
    }
}