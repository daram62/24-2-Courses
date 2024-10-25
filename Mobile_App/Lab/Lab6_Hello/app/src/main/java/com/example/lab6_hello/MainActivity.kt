package com.example.lab6_hello

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME = "extra_key_name"
        const val EXTRA_AGE = "extra_key_age"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnNewActivity = findViewById<Button>(R.id.button)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)

        btnNewActivity.setOnClickListener{

            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toInt()

            val intent = Intent(this, SecondActivity::class.java).apply{
                putExtra("EXTRA_NAME",name)
                putExtra("EXTRA_AGE",age)
            }
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)

        editTextName.text.clear()
        editTextAge.text.clear()
    }
}