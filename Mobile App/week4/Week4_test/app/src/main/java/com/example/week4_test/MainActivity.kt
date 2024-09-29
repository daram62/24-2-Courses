package com.example.week4_test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout = findViewById<LinearLayout>(R.id.subLayout)
        val button = findViewById<Button>(R.id.buttonShowSubLayout)

        button.setOnClickListener {
            val layoutInflater: LayoutInflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            layoutInflater.inflate(R.layout.sub_layout, linearLayout, true)

            val img1 = findViewById<ImageView>(R.id.imageView)
            val img2 = findViewById<ImageView>(R.id.imageView2)

            img1.setImageResource(R.drawable.bbq)
            img2.setImageResource(R.drawable.bhc)
        }

        val chickenList = arrayListOf(
            hashMapOf("name" to "BBQ", "image" to R.drawable.bbq),
            hashMapOf("name" to "BHC", "image" to R.drawable.bhc),
            hashMapOf("name" to "피자나라 치킨공주", "image" to R.drawable.pizzanarachickengongju),
            hashMapOf("name" to "굽네치킨", "image" to R.drawable.goobne)
        )

        val pizzaList = arrayListOf(
            hashMapOf("name" to "Domino Pizza", "image" to R.drawable.domino),
            hashMapOf("name" to "Pizza Hut", "image" to R.drawable.pizzahut),
            hashMapOf("name" to "피자나라 치킨공주", "image" to R.drawable.pizzanarachickengongju)
        )

        val hamburgerList = arrayListOf(
            hashMapOf("name" to "McDonald's", "image" to R.drawable.mcdonalds),
            hashMapOf("name" to "Lotteria", "image" to R.drawable.lotteria),
            hashMapOf("name" to "Burger King", "image" to R.drawable.burgerking),
            hashMapOf("name" to "Mom's Touch", "image" to R.drawable.momstouch)
        )

        val listView = findViewById<ListView>(R.id.listView)
        val buttonPizza = findViewById<Button>(R.id.buttonPizza)
        val buttonHamburger = findViewById<Button>(R.id.buttonHamburger)
        val buttonChicken = findViewById<Button>(R.id.buttonChicken)

        val from = arrayOf("name", "image")  // 데이터의 키값
        val to = intArrayOf(R.id.itemTextView, R.id.itemImageView)  // 매핑할 뷰 ID

        var adapter = SimpleAdapter(this, pizzaList, R.layout.item, from, to)
        listView.adapter = adapter

        buttonPizza.setOnClickListener {
            adapter = SimpleAdapter(this, pizzaList, R.layout.item, from, to)
            listView.adapter = adapter
        }

        buttonHamburger.setOnClickListener {
            adapter = SimpleAdapter(this, hamburgerList, R.layout.item, from, to)
            listView.adapter = adapter
        }

        buttonChicken.setOnClickListener {
            adapter = SimpleAdapter(this, chickenList, R.layout.item, from, to)
            listView.adapter = adapter
        }
    }
}