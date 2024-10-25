package com.example.chatroom
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

// ChatRoomAdapter: BaseAdapter를 상속하여 ListView에 데이터를 연결하는 어댑터
class ChatRoomAdapter(val data: ArrayList<ChatRoom>, val context: Context): BaseAdapter() {

    // 리스트 항목의 개수를 반환
    override fun getCount(): Int {
        return data.size
    }

    // 해당 위치의 항목 데이터를 반환
    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    // 해당 항목의 고유 ID를 반환 (현재는 0으로 설정)
    override fun getItemId(p0: Int): Long {
        return 0
    }

    // ListView의 각 항목에 대한 뷰를 생성하여 반환
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        // LayoutInflater를 사용하여 XML 레이아웃을 View 객체로 변환
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.item_chatroom, null)

        // View에서 ImageView와 TextView를 찾아 변수에 저장
        val textViewName = generatedView.findViewById<TextView>(R.id.textViewRoomName)
        val textViewChat = generatedView.findViewById<TextView>(R.id.textViewRecentChat)
        val textViewGroupNumber = generatedView.findViewById<TextView>(R.id.textViewGroupSize)
        val textViewTime = generatedView.findViewById<TextView>(R.id.textViewLastTime)
        val imageViewThumbnail = generatedView.findViewById<ImageView>(R.id.imageViewProfile)
        val textUnreadMessageCount = generatedView.findViewById<TextView>(R.id.unreadMessageCount)

        // 데이터를 View에 설정
        textViewName.text = data[p0].name  // 채팅방 이름 설정
        textViewChat.text = data[p0].lastChat  // 마지막 채팅 내용 설정
        textViewTime.text = data[p0].lastTime  // 마지막 채팅 시간이 설정
        imageViewThumbnail.setImageResource(data[p0].thumbnail)  // 프로필 이미지 설정

        // 그룹 넘버가 1인 경우 숨기고, 그렇지 않으면 그룹 넘버 표시
        if (data[p0].groupNumber == 1) {
            textViewGroupNumber.visibility = View.GONE  // 보이지 않도록 설정
        } else {
            textViewGroupNumber.visibility = View.VISIBLE  // 보이도록 설정
            textViewGroupNumber.text = data[p0].groupNumber.toString()  // 그룹 넘버 설정
        }

        // 읽지 않은 메시지 수 설정
        textUnreadMessageCount.text = data[p0].unreadMessageCount.toString()

        // 생성된 View 반환
        return generatedView
    }
}