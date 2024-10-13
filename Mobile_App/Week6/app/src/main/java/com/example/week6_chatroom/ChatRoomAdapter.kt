package com.example.week6_chatroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ChatRoomAdapter(val data: ArrayList<ChatRoom>, val context: Context): BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.item_chatroom, null)

        val textViewName = generatedView.findViewById<TextView>(R.id.textViewRoomName)
        val textViewChat = generatedView.findViewById<TextView>(R.id.textViewRecentChat)
        val textViewGroupNumber = generatedView.findViewById<TextView>(R.id.textViewGroupSize)
        val textViewTime = generatedView.findViewById<TextView>(R.id.textViewLastTime)
        val imageViewThumbnail = generatedView.findViewById<ImageView>(R.id.imageViewProfile)
        val textViewUnreadMessageCount = generatedView.findViewById<TextView>(R.id.textViewUnreadMessageCount)

        textViewName.text = data[position].name
        textViewChat.text = data[position].lastChat
        textViewTime.text = data[position].lastTime
        imageViewThumbnail.setImageResource(data[position].thumbnail)

        // Hide group number if it is 1
        if (data[position].groupNumber > 1) {
            textViewGroupNumber.visibility = View.VISIBLE
            textViewGroupNumber.text = "${data[position].groupNumber}"
        } else {
            textViewGroupNumber.visibility = View.GONE
        }

        // Show unread message count if greater than 0
        if (data[position].unreadMessageCount > 0) {
            textViewUnreadMessageCount.visibility = View.VISIBLE
            textViewUnreadMessageCount.text = data[position].unreadMessageCount.toString()
        } else {
            textViewUnreadMessageCount.visibility = View.GONE
        }

        return generatedView
    }
}