package com.example.week7_hello

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // "확인" 버튼 참조
        val btnNewActivity = findViewById<Button>(R.id.buttonNewActivity)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)

        btnNewActivity.setOnClickListener {

            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull()

            // 입력값 검증
            if (name.isBlank()) {
                Toast.makeText(applicationContext, "Enter the Name.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (age == null) {
                Toast.makeText(applicationContext, "Enter the Age.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Activity1로 변경
            val intent = Intent(this, Activity1::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_AGE", age)
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