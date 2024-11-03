package com.example.lab7_alarm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTime: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 참조
        editTextTime = findViewById(R.id.editText_Time)
        editTextDescription = findViewById(R.id.editText_Description)
        button = findViewById(R.id.button)

        // 버튼 클릭 리스너 설정
        button.setOnClickListener {
            val timeText = editTextTime.text.toString()
            val description = editTextDescription.text.toString()

            if (timeText.isEmpty()) {
                // 시간 입력이 비어있을 경우 처리
                Toast.makeText(this, "시간을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 시간 형식 "HH:MM"으로 입력되었는지 확인
            val timeParts = timeText.split(":")
            if (timeParts.size != 2) {
                Toast.makeText(this, "시간을 HH:MM 형식으로 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hour = timeParts[0].toIntOrNull()
            val minute = timeParts[1].toIntOrNull()

            if (hour == null || minute == null || hour !in 0..23 || minute !in 0..59) {
                // 유효하지 않은 시간 또는 분일 경우 처리
                Toast.makeText(this, "유효한 시간을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // SecondActivity로 데이터 전달
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("EXTRA_HOUR", hour)
                putExtra("EXTRA_MINUTE", minute)
                putExtra("EXTRA_DESCRIPTION", description)
            }
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        editTextTime = findViewById(R.id.editText_Time)
        editTextDescription = findViewById(R.id.editText_Description)

        editTextTime.text.clear()
        editTextDescription.text.clear()
    }
}