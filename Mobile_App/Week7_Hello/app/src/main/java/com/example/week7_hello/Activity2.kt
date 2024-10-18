package com.example.week7_hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2) // 빈 레이아웃 사용

        // 전달된 데이터 받기
        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getIntExtra("EXTRA_AGE", -1)

        // Toast 메시지 표시
        if (name != null && age != -1) {
            Toast.makeText(applicationContext, "Welcome, $name ($age years old)!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "Invalid data received!", Toast.LENGTH_SHORT).show()
        }
    }
}