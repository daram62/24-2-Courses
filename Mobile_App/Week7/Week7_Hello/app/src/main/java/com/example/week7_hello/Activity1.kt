package com.example.week7_hello

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        // 전달된 데이터 받기
        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getIntExtra("EXTRA_AGE", -1)

        // 텍스트뷰에 메시지 표시
        val textViewMessage = findViewById<TextView>(R.id.textViewMessage)
        textViewMessage.text = "You typed name $name and age $age, is that right?"

        // "Yes" 버튼 설정
        val buttonYes = findViewById<Button>(R.id.buttonYes)
        buttonYes.setOnClickListener {
            // Activity2로 데이터 전달 및 시작
            val intent = Intent(this, Activity2::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_AGE", age)
            }
            startActivity(intent)
        }

        // "No" 버튼 설정
        val buttonNo = findViewById<Button>(R.id.buttonNo)
        buttonNo.setOnClickListener {
            // main acitivity로 이동
            finish()
        }
    }
}