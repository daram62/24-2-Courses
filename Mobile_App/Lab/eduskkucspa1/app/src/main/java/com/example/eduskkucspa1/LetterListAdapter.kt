package com.example.eduskkucspa1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

// 데이터 클래스
data class LetterItem(val letter: Char, val status: Status)

class LetterListAdapter(context: Context, private val letters: MutableList<LetterItem>) :
    ArrayAdapter<LetterItem>(context, 0, letters) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val letterItem = letters[position]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_letter, parent, false)

        val letterView = view.findViewById<TextView>(R.id.textViewLetter)
        letterView.text = letterItem.letter.toString().uppercase()

        when (letterItem.status) {
            Status.GREEN -> {
                letterView.setBackgroundColor(Color.parseColor("#FF99F691")) // Green 배경
                letterView.setTextColor(Color.parseColor("#000000")) // 검은색 텍스트
            }
            Status.YELLOW -> {
                letterView.setBackgroundColor(Color.parseColor("#FFFFE46F")) // Yellow 배경
                letterView.setTextColor(Color.parseColor("#000000")) // 검은색 텍스트
            }
            Status.GRAY -> {
                letterView.setBackgroundColor(Color.parseColor("#FF787C7E")) // Gray 배경
                letterView.setTextColor(Color.parseColor("#FFFFFF")) // 흰색 텍스트
            }
        }

        return view
    }

    fun addLetter(letter: LetterItem) {
        letters.add(letter)
        notifyDataSetChanged()
    }
}