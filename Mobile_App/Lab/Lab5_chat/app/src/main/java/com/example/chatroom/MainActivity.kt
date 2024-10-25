package com.example.chatroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // activity_main.xml 레이아웃을 화면에 설정

        // 채팅방 데이터를 저장하는 ArrayList 생성
        val items = ArrayList<ChatRoom>()

        // 채팅방 리스트에 항목 추가 (각 항목에는 이름, 마지막 채팅, 이미지, 그룹 크기, 마지막 시간, 읽지 않은 메시지 수가 포함됨)
        items.add(ChatRoom("Kakao T", "Please leave review.", R.drawable.ic_launcher_background, 1, "8:24 p.m.", 12))
        items.add(ChatRoom("SKKU Software", "Anybody taking MAP lecture? This week lab session is too hard. Can anybody give me the hint? I will be very happy if you help. If nobody help me, I will be very sad.", R.drawable.ic_launcher_background, 512, "7:15 p.m.", 5))
        items.add(ChatRoom("Brother", "Hey.", R.drawable.ic_launcher_background, 1, "4:21 p.m.", 2))
        items.add(ChatRoom("Family", "Emoji", R.drawable.ic_launcher_background, 4, "4:05 p.m.", 1))
        items.add(ChatRoom("Study group", "See you tomorrow!", R.drawable.ic_launcher_background, 5, "4:01 p.m.", 2))
        items.add(ChatRoom("Yogiyo", "How was the food?", R.drawable.ic_launcher_background, 1, "3:24 p.m.", 1))
        items.add(ChatRoom("lorem ipsum", "dolor", R.drawable.ic_launcher_background, 6, "2:22 p.m.", 123))
        items.add(ChatRoom("Placeholder", "Placeholder", R.drawable.ic_launcher_background, 12, "11:58 a.m.", 10))

        // ChatRoomAdapter를 생성하여 ArrayList와 연결 (어댑터는 데이터를 리스트에 표시하는 역할)
        val myAdapter = ChatRoomAdapter(items, applicationContext)

        // ListView를 XML에서 찾아서 어댑터를 설정
        val listView = findViewById<ListView>(R.id.listViewChatRoom)
        listView.adapter = myAdapter  // 리스트뷰에 어댑터를 연결
    }
}