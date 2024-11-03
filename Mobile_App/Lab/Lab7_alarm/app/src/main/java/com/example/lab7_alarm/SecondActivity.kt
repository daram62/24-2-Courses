package com.example.lab7_alarm

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var buttonOk: Button
    private lateinit var buttonCancel: Button

    private var hour: Int = 0
    private var minute: Int = 0
    private var description: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // 뷰 참조
        textView = findViewById(R.id.textView)
        buttonOk = findViewById(R.id.buttonOk)
        buttonCancel = findViewById(R.id.buttonCancel)

        // MainActivity로부터 데이터 받기
        intent?.let {
            hour = it.getIntExtra("EXTRA_HOUR", 0)
            minute = it.getIntExtra("EXTRA_MINUTE", 0)
            description = it.getStringExtra("EXTRA_DESCRIPTION") ?: ""
        }

        // TextView 설정
        val timeString = String.format("%02d:%02d", hour, minute)
        textView.text = "Do you want to set alarm on time $timeString with description '$description'?"

        // Cancel 버튼 클릭 리스너
        buttonCancel.setOnClickListener {
            finish() // 액티비티 종료
        }

        // OK 버튼 클릭 리스너
        buttonOk.setOnClickListener {
            // 알람 설정을 위한 인텐트 생성
            val alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, hour)
                putExtra(AlarmClock.EXTRA_MINUTES, minute)
                putExtra(AlarmClock.EXTRA_MESSAGE, description)
                // FLAG_ACTIVITY_NEW_TASK를 설정하면 새로운 태스크에서 실행됩니다.
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            // 알람 앱이 설치되어 있는지 확인
            if (alarmIntent.resolveActivity(packageManager) != null) {
                startActivity(alarmIntent)
            } else {
                Toast.makeText(this, "알람 앱을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            }

            finish() // 액티비티 종료
        }
    }
}