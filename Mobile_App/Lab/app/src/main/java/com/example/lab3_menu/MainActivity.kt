package com.example.lab3_menu

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {

    private val foods = listOf(
        Pair("Pizza", R.drawable.pizza),
        Pair("chicken", R.drawable.chicken),
        Pair("hamburger", R.drawable.hamburger),
        Pair("ramen", R.drawable.ramen)
    )
    private var counter = 0
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Food Menu"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)

        val left_btn = findViewById<Button>(R.id.button1)
        val right_btn = findViewById<Button>(R.id.button2)

        updateUI(counter)

        right_btn.setOnClickListener{
            counter ++
            if(counter >= foods.size){
                Toast.makeText(this@MainActivity, "Last Image", Toast.LENGTH_SHORT).show()
                counter = foods.size - 1
            }
            updateUI(counter)
        }

        left_btn.setOnClickListener{
            counter--
            if(counter < 0) {
                Toast.makeText(this@MainActivity, "First Image", Toast.LENGTH_SHORT).show()
                counter = 0
            }
            updateUI(counter)
        }
        Log.i("counter", "counter: $counter")
    }
    private fun updateUI(index: Int) {
        val (name, imageRes) = foods[index]
        textView.text = name
        imageView.setImageResource(imageRes)
    }
}