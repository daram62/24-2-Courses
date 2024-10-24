package com.example.lab4_shop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 엣지 투 엣지 레이아웃 활성화
        enableEdgeToEdge()

        // activity_main 레이아웃을 화면에 표시
        setContentView(R.layout.activity_main)

        // 시스템 바에 맞춰 레이아웃의 여백을 조정하는 리스너 추가
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 레이아웃과 버튼들을 찾고 변수에 저장
        val linearLayout = findViewById<LinearLayout>(R.id.subLayout)
        val button = findViewById<Button>(R.id.button)

        // 각 음식 카테고리를 선택할 버튼들
        val btnPizza = findViewById<Button>(R.id.btnPizza)
        val btnHam = findViewById<Button>(R.id.btnHam)
        val btnChi = findViewById<Button>(R.id.btnChi)

        // 버튼 클릭 시 추가 레이아웃을 동적으로 생성
        button.setOnClickListener {
            // 레이아웃 인플레이터를 통해 추가 레이아웃을 삽입
            val layoutInflater: LayoutInflater =
                applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // sub_layout을 동적으로 linearLayout에 추가
            layoutInflater.inflate(R.layout.sub_layout, linearLayout, true)

            // 새로 추가된 레이아웃에서 이미지뷰를 찾아 이미지 설정
            val img1 = findViewById<ImageView>(R.id.imageView)
            val img2 = findViewById<ImageView>(R.id.imageView2)

            img1.setImageResource(R.drawable.bbq)
            img2.setImageResource(R.drawable.bhc)
        }

        // 치킨, 피자, 햄버거 리스트 데이터를 정의 (각각 음식의 이미지와 이름)
        val chicken_items = arrayListOf(
            Restaurant(R.drawable.bbq, "BBQ"),
            Restaurant(R.drawable.bhc, "BHC"),
            Restaurant(R.drawable.pizzanarachickengongju, "Pizza Nara Chicken Gongju"),
            Restaurant(R.drawable.goobne, "Goobne")
        )
        val pizza_items = arrayListOf(
            Restaurant(R.drawable.domino, "Domino"),
            Restaurant(R.drawable.pizzahut, "Pizza Hut"),
            Restaurant(R.drawable.pizzanarachickengongju, "Pizza Nara Chicken Gongju"),
            Restaurant(R.drawable.momstouch, "Mom's Touch")
        )
        val ham_items = arrayListOf(
            Restaurant(R.drawable.mcdonalds, "McDonald's"),
            Restaurant(R.drawable.lotteria, "Lotteria"),
            Restaurant(R.drawable.burgerking, "Burger King"),
            Restaurant(R.drawable.momstouch, "Mom's Touch")
        )

        // 리스트뷰를 찾고, 각 카테고리에 맞는 어댑터를 설정
        val mainList = findViewById<ListView>(R.id.listView)
        val pi_listAdapter = CustomAdapter(this, pizza_items)
        val chi_listAdapter = CustomAdapter(this, chicken_items)
        val ham_listAdapter = CustomAdapter(this, ham_items)

        // 기본적으로 피자 리스트 어댑터를 리스트뷰에 설정
        mainList.adapter = pi_listAdapter

        // 버튼 클릭 시 각 카테고리별 리스트로 어댑터를 변경
        btnPizza.setOnClickListener {
            mainList.adapter = pi_listAdapter
        }
        btnChi.setOnClickListener {
            mainList.adapter = chi_listAdapter
        }
        btnHam.setOnClickListener {
            mainList.adapter = ham_listAdapter
        }
    }
}